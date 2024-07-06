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

package com.noob.base.batchHandle.controller;

import com.noob.base.batchHandle.entity.model.TLimit;
import com.noob.base.batchHandle.entity.model.TUserBatch;
import com.noob.base.batchHandle.service.TLimitService;
import com.noob.base.batchHandle.util.RandomGenEntityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class BatchInsertController {

    @Autowired
    private TLimitService tLimitService;

    // http://127.0.0.1:8080/hello?name=lisi
    @RequestMapping("/hello")
    @ResponseBody
    public String hello(@RequestParam(name = "name", defaultValue = "unknown user") String name) {
        return "Hello " + name;
    }

    /**
     * 模拟批量插入操作方式1
     * @return
     */
    @RequestMapping("/batchInsertTLimit1")
    @ResponseBody
    public String batchInsertTLimit1() {
        long start = System.currentTimeMillis();
        List<TLimit> limits = RandomGenEntityUtil.genTLimit(1000000);
        // 批量插入（默认单次批量提交1000条数据）
        // tLimitService.saveBatch(limits);
        // 批量插入（指定batchSize为10000）
        tLimitService.saveBatch(limits,50000);
        long end = System.currentTimeMillis();
        System.out.println("数据批量插入耗时：" + (end - start) / 1000 + "s");
        return "success";
    }

    /**
     * 模拟批量插入操作方式1
     * @return
     */
    @RequestMapping("/exportInsertTLimitSQL")
    @ResponseBody
    public String exportInsertTLimitSQL() throws Exception {
        long start = System.currentTimeMillis();
        List<TLimit> limits = RandomGenEntityUtil.genTLimit(1000000);
        String filePath = "D:\\Desktop\\tmp\\insertTLimit.sql";
        tLimitService.exportInsertSQL(limits,filePath);
        long end = System.currentTimeMillis();
        System.out.println("数据导出耗时：" + (end - start) / 1000 + "s");
        return "success";
    }

}
