package com.noob.base.coverage.helper;

import org.junit.Before;
import org.junit.Test;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.*;

/**
 * Test class for {@link EqualsHashCodeFullCoverageUtil}.
 * This test focuses on the protected method getDefaultValueForGenericType.
 */
public class EqualsHashCodeFullCoverageUtilForGetNonNullValueForFieldTest {

    private EqualsHashCodeFullCoverageUtil<?> coverageUtil;
    private Method methodToTest;

    /**
     * A helper class with various fields to test different reflection scenarios.
     */
    @SuppressWarnings("unused")
    public static class TestData {
        // For happy path: ParameterizedType with a Class as type argument
        public List<String> stringList;
        public Map<Integer, Boolean> map;

        // For path where generic type is not a ParameterizedType
        public String nonGenericField;

        // For path where type argument is not a Class (it's a WildcardType)
        public List<?> wildcardList;
    }

    @Before
    public void setUp() throws Exception {
        // The method under test is static-like and doesn't depend on the instance state,
        // so we can pass a dummy supplier.
        coverageUtil = new EqualsHashCodeFullCoverageUtil<>(Object::new);
        // Make the protected method accessible for testing via reflection
        methodToTest = EqualsHashCodeFullCoverageUtil.class.getDeclaredMethod(
                "getDefaultValueForGenericType", Field.class, int.class);
        methodToTest.setAccessible(true);
    }

    /**
     * Helper to invoke the protected method under test.
     */
    private Object invokeMethod(Field field, int position) throws Exception {
        return methodToTest.invoke(coverageUtil, field, position);
    }

    /**
     * A single, comprehensive test method to cover all execution paths of getDefaultValueForGenericType.
     */
    @Test
    public void testGetDefaultValueForGenericType_AllPaths() throws Exception {
        // --- Path 1: Happy path, where a generic type is successfully extracted ---
        Field stringListField = TestData.class.getField("stringList");
        // From your provided source, getDefaultValue(String.class) returns "".
        assertEquals("Happy path should return default value for String", "", invokeMethod(stringListField, 0));

        Field mapField = TestData.class.getField("map");
        // From your provided source, getDefaultValue(Boolean.class) returns false.
        assertEquals("Happy path should return default value for Boolean", false, invokeMethod(mapField, 1));

        // --- Path 2: Fallback paths, where "defaultValue" should be returned ---
        // Case A: Not a ParameterizedType
        Field nonGenericField = TestData.class.getField("nonGenericField");
        assertEquals("Fallback for non-generic type", "defaultValue", invokeMethod(nonGenericField, 0));

        // Case B: Position is out of bounds
        assertEquals("Fallback for position out of bounds", "defaultValue", invokeMethod(stringListField, 1));

        // Case C: Type argument is not a Class (e.g., a WildcardType)
        Field wildcardListField = TestData.class.getField("wildcardList");
        assertEquals("Fallback for wildcard type argument", "defaultValue", invokeMethod(wildcardListField, 0));

        // --- Path 3: Exception path, which should be caught and handled ---
        // Passing a null field will cause a NullPointerException inside the method, which should be caught.
        assertEquals("Exception path should be caught and return fallback", "defaultValue", invokeMethod(null, 0));
    }
}