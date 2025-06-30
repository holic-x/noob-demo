package com.noob.base.coverage.mockEntity;

// --- Classes for Failure Scenarios --- get 构造器返回异常 测试数据
public class ExceptionInGetterData {
    public String getName() {
        throw new IllegalStateException("Getter exception");
    }

    // Add other methods to pass other checks
    @Override
    public boolean equals(Object o) {
        return true;
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public String toString() {
        return "ExceptionInGetterData";
    }
}