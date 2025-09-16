package com.noob.base.runtimeFailures.oom.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.List;

@RestController
public class JvmParamController {

    // 提供接口查看启动参数
    @GetMapping("/jvm-params")
    public List<String> getJvmParams() {
        RuntimeMXBean runtimeMxBean = ManagementFactory.getRuntimeMXBean();
        return runtimeMxBean.getInputArguments();  // 返回所有JVM启动参数
    }
}