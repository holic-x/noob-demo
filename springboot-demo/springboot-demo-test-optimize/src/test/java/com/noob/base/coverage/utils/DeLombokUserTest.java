package com.noob.base.coverage.utils;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 针对 DeLombokUser 的全面单元测试，旨在覆盖其所有方法的逻辑分支
 */
public class DeLombokUserTest {

    private DeLombokUser user1;
    private DeLombokUser user2;

    @Before
    public void setUp() {
        // 准备两个内容完全相同的对象，用于对称性测试
        user1 = new DeLombokUser();
        user1.setId(1L);
        user1.setUsername("testuser");
        user1.setEmail("test@example.com");

        user2 = new DeLombokUser();
        user2.setId(1L);
        user2.setUsername("testuser");
        user2.setEmail("test@example.com");
    }

    @Test
    public void testGettersAndSettersAndNoArgConstructor() {
        // 测试无参构造函数和 Setters/Getters
        DeLombokUser user = new DeLombokUser();
        assertNull(user.getId());
        assertNull(user.getUsername());
        assertNull(user.getEmail());

        user.setId(2L);
        user.setUsername("anotheruser");
        user.setEmail("another@example.com");

        assertEquals(Long.valueOf(2L), user.getId());
        assertEquals("anotheruser", user.getUsername());
        assertEquals("another@example.com", user.getEmail());
    }

    @Test
    public void testToString() {
        // 测试 toString() 方法的输出是否包含了所有字段
        String toStringResult = user1.toString();
        assertTrue("toString() 应包含 id", toStringResult.contains("id=1"));
        assertTrue("toString() 应包含 username", toStringResult.contains("username=testuser"));
        assertTrue("toString() 应包含 email", toStringResult.contains("email=test@example.com"));
    }

    @Test
    public void testEqualsAndHashCodeContract() {
        // 1. 自反性: x.equals(x) 必须为 true
        assertEquals("对象应等于其自身", user1, user1);

        // 2. 对称性: 如果 x.equals(y) 为 true, 那么 y.equals(x) 也必须为 true
        assertEquals("内容相同的对象应相等", user1, user2);
        assertEquals("相等性必须是对称的", user2, user1);

        // 3. hashCode 协定: 内容相同的对象 hashCode 必须相同
        assertEquals("内容相同的对象 hashCode 应相同", user1.hashCode(), user2.hashCode());

        // 4. 与 null 和不同类型的对象比较
        assertNotEquals("对象不应等于 null", user1, null);
        assertNotEquals("对象不应等于不同类型的对象", user1, new Object());
    }

    @Test
    public void testEquals_FieldByFieldInequality() {
        // 逐个字段地测试不相等的情况，以触发 equals 方法中的每个 'return false' 分支
        DeLombokUser diffIdUser = new DeLombokUser();
        diffIdUser.setId(99L);
        diffIdUser.setUsername("testuser");
        diffIdUser.setEmail("test@example.com");
        assertNotEquals("id 不同时对象不应相等", user1, diffIdUser);

        DeLombokUser diffUsernameUser = new DeLombokUser();
        diffUsernameUser.setId(1L);
        diffUsernameUser.setUsername("different_user");
        diffUsernameUser.setEmail("test@example.com");
        assertNotEquals("username 不同时对象不应相等", user1, diffUsernameUser);

        DeLombokUser diffEmailUser = new DeLombokUser();
        diffEmailUser.setId(1L);
        diffEmailUser.setUsername("testuser");
        diffEmailUser.setEmail("different@example.com");
        assertNotEquals("email 不同时对象不应相等", user1, diffEmailUser);
    }

    @Test
    public void testEqualsAndHashCode_WithNullFields() {
        // 这是确保高分支覆盖率最关键的测试

        // 场景1: 两个对象的所有字段都为 null
        DeLombokUser allNull1 = new DeLombokUser();
        DeLombokUser allNull2 = new DeLombokUser();
        assertEquals("所有字段都为 null 的两个对象应相等", allNull1, allNull2);
        assertEquals("所有字段都为 null 的两个对象的 hashCode 应相等", allNull1.hashCode(), allNull2.hashCode());

        // 场景2: 逐个字段测试 null vs 非 null
        DeLombokUser userWithNullId = new DeLombokUser();
        userWithNullId.setUsername("testuser");
        userWithNullId.setEmail("test@example.com");
        assertNotEquals("一个 id 为 null，另一个不为 null，对象不应相等", user1, userWithNullId);
        assertNotEquals("一个 id 不为 null，另一个为 null，对象不应相等", userWithNullId, user1);

        DeLombokUser userWithNullUsername = new DeLombokUser();
        userWithNullUsername.setId(1L);
        userWithNullUsername.setEmail("test@example.com");
        assertNotEquals("一个 username 为 null，另一个不为 null，对象不应相等", user1, userWithNullUsername);
        assertNotEquals("一个 username 不为 null，另一个为 null，对象不应相等", userWithNullUsername, user1);

        DeLombokUser userWithNullEmail = new DeLombokUser();
        userWithNullEmail.setId(1L);
        userWithNullEmail.setUsername("testuser");
        assertNotEquals("一个 email 为 null，另一个不为 null，对象不应相等", user1, userWithNullEmail);
        assertNotEquals("一个 email 不为 null，另一个为 null，对象不应相等", userWithNullEmail, user1);
    }

    @Test
    public void testCanEqual() {
        // 1. 与同类型对象比较
        assertTrue("canEqual 应为 true 对于同类型实例", user1.canEqual(user2));

        // 2. 与子类对象比较
        class SubDeLombokUser extends DeLombokUser {}
        SubDeLombokUser subUser = new SubDeLombokUser();
        assertTrue("canEqual 应为 true 对于子类实例", user1.canEqual(subUser));

        // 3. 与不相关类型的对象比较
        assertFalse("canEqual 应为 false 对于不相关类型", user1.canEqual(new Object()));

        // 4. 测试 equals 中 !other.canEqual(this) 的分支
        class BadSubDeLombokUser extends DeLombokUser {
            @Override
            protected boolean canEqual(Object other) {
                // 这个子类“不认为”父类实例可以与它相等
                return false;
            }
        }
        BadSubDeLombokUser badSubUser = new BadSubDeLombokUser();
        badSubUser.setId(1L);
        badSubUser.setUsername("testuser");
        badSubUser.setEmail("test@example.com");

        // 此时 user1.equals(badSubUser) 会调用 badSubUser.canEqual(user1)，返回 false
        assertNotEquals("当 other.canEqual(this) 返回 false 时，equals 应返回 false", user1, badSubUser);
    }
}