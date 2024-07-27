package com.noob.base.demo.util;

import org.junit.jupiter.api.Test;

class OperatorUtilTest {

    @Test
    void add() {
        int res = OperatorUtil.add(1, 2);
        assert res == 3;
    }

    @Test
    void sub() {
        int res = OperatorUtil.sub(2, 1);
        assert res == 1;
    }

    @Test
    void mul() {
        int res = OperatorUtil.mul(2, 1);
        assert res == 2;
    }

    @Test
    void div() {
        int res1 = OperatorUtil.div(2, 1);
        assert res1 == 2;

        int res2 = OperatorUtil.div(2, 0);
        assert res2 == 0;
    }
}