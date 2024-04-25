package com.noob.redis;

import com.noob.redis.service.CacheService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootDemoRedisApplicationTests {

    @Autowired
    private CacheService cacheService;

    @Test
    void add() {
        cacheService.add("test", 1234);
    }

}
