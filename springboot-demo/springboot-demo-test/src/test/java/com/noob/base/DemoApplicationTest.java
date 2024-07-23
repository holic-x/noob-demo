package com.noob.base;

import com.noob.base.demo.util.OperatorUtil;

//@SpringBootTest
class DemoApplicationTest {

//    @Test
    void main() {
        OperatorUtil.add(1,2);
        OperatorUtil.sub(1,2);
        OperatorUtil.mul(1,2);
        OperatorUtil.div(1,2);
        OperatorUtil.div(1,0);
    }
}