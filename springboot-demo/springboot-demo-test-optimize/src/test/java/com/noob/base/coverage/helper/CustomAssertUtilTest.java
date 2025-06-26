package com.noob.base.coverage.helper;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * CustomAssertUtilTest：UT for CustomAssertUtil
 */
public class CustomAssertUtilTest {

    @Test
    public void test_coverage_constructor() throws Exception {
        CustomAssertUtil customAssertUtil = new CustomAssertUtil();
        assertNotNull(customAssertUtil);
    }

    @Test
    public void test_assertTrue() {
        CustomAssertUtil.assertTrue(true, "assertTrue true");
        CustomAssertUtil.assertTrue(false, "assertTrue false");
    }

    @Test
    public void test_assertFalse() {
        CustomAssertUtil.assertFalse(true, "assertFalse true");
        CustomAssertUtil.assertFalse(false, "assertFalse false");
    }

    @Test
    public void test_assertEquals() {
        CustomAssertUtil.assertEquals("hello", "hello", "相同");
        CustomAssertUtil.assertEquals("hello", "world", "不相同");
    }

    @Test
    public void test_assertNotEquals() {
        CustomAssertUtil.assertNotEquals("hello", "hello", "相同");
        CustomAssertUtil.assertNotEquals("hello", "world", "不相同");
    }

    @Test
    public void test_assertNotNull() {
        CustomAssertUtil.assertNotNull("hello", "非null");
        CustomAssertUtil.assertNotNull(null, "null");
    }

}
