/*
 * Copyright 2013-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.springbootdemoredission.controller;

import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RedisController {

    @Autowired
    private RedissonClient redissonClient;

    @RequestMapping("/publish")
    @ResponseBody
    public String publishMessage() {
        System.out.println("..........publish..........");
        RTopic topic = redissonClient.getTopic("myTopic");
        topic.publish("Hello, Redisson!");
        return "success";
    }

    @RequestMapping("/subscribe")
    @ResponseBody
    public String subscribeMessage() {
        System.out.println("..........subscribe..........");
        RTopic topic = redissonClient.getTopic("myTopic");
        topic.addListener(String.class, (message, channel) -> {
            System.out.println("now Received message: " + message);
        });
        return "success";
    }

}
