package com.noob.redis;

import com.noob.redis.demo.OperatorUtil;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DemoApplicationTest {

    @Test
    void main() {
        OperatorUtil.add(1,2);
        OperatorUtil.sub(1,2);
        OperatorUtil.mul(1,2);
        OperatorUtil.div(1,2);
        OperatorUtil.div(1,0);
    }
}