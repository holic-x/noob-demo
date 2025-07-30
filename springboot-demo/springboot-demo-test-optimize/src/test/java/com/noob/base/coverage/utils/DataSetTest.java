package com.noob.base.coverage.utils;

import lombok.Data;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * 定义CoverageUtil相关的测试目标数据
 * 将测试覆盖的测试集放在此处，避免被sonar扫描识别为source code进而影响覆盖率
 */
public class DataSetTest {

    @Ignore
    @Test
    public void test_coverage() {
        assertTrue(Boolean.TRUE);
    }

}

// =========================================================================================================
// -  定义覆盖多个基础类型的实体：测试所有类型的字段（字段类型兼容测试）
// =========================================================================================================
@Data
class AllTypesData {
    private boolean pBoolean;
    private byte pByte;
    private short pShort;
    private int pInt;
    private long pLong;
    private float pFloat;
    private double pDouble;
    private char pChar;
    private Boolean wBoolean;
    private Byte wByte;
    private Short wShort;
    private Integer wInteger;
    private Long wLong;
    private Float wFloat;
    private Double wDouble;
    private Character wChar;
    private String string;
    private Object object; // For null default
}


// =========================================================================================================
// -  普通实体类（Lombok@Data 经过DeLombok转化） DeLombok @Data 实体类
// =========================================================================================================
class DeLombokUser {
    private Long id;
    private String username;
    private String email;

    public DeLombokUser() {
    }

    public Long getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public String getEmail() {
        return this.email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof DeLombokUser)) return false;
        final DeLombokUser other = (DeLombokUser) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$username = this.getUsername();
        final Object other$username = other.getUsername();
        if (this$username == null ? other$username != null : !this$username.equals(other$username)) return false;
        final Object this$email = this.getEmail();
        final Object other$email = other.getEmail();
        if (this$email == null ? other$email != null : !this$email.equals(other$email)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof DeLombokUser;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $username = this.getUsername();
        result = result * PRIME + ($username == null ? 43 : $username.hashCode());
        final Object $email = this.getEmail();
        result = result * PRIME + ($email == null ? 43 : $email.hashCode());
        return result;
    }

    public String toString() {
        return "DeLombokUser(id=" + this.getId() + ", username=" + this.getUsername() + ", email=" + this.getEmail() + ")";
    }
}


