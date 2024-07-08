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
import com.noob.base.batchHandle.service.TLimitService;
import com.noob.base.batchHandle.util.RandomGenEntityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.UUID;

@Controller
public class BatchInsertController {

    @Autowired
    private TLimitService tLimitService;

    // 文件目标生成路径
    // private String TARGET_PATH  = "D:\\Desktop\\tmp\\";
    private String TARGET_PATH  = "/Users/holic-x/tmp/";

    // http://127.0.0.1:8080/hello?name=lisi
    @RequestMapping("/hello")
    @ResponseBody
    public String hello(@RequestParam(name = "name", defaultValue = "unknown user") String name) {
        return "Hello " + name;
    }

    /**
     * 模拟批量插入操作方式（通过MyBatis-Plus的批量导入功能）：指定分批条件进行测试
     * @return
     */
    @RequestMapping("/batchInsertTLimitByMB")
    @ResponseBody
    public String batchInsertTLimitByMB() {
        long start = System.currentTimeMillis();
        List<TLimit> limits = RandomGenEntityUtil.genTLimit(1,1000000);
        // 批量插入（默认单次批量提交1000条数据）
        // tLimitService.saveBatch(limits);

        // 批量插入（指定单次的batchSize为10000）
         tLimitService.saveBatch(limits,50000);

        long end = System.currentTimeMillis();
        System.out.println("数据批量插入耗时：" + (end - start) / 1000 + "s");
        return "success";
    }

    /**
     * 模拟批量数据导出
     * 生成数据格式为insert类型的SQL语句
     * @return
     */
    @RequestMapping("/exportInsertTLimitSQL")
    @ResponseBody
    public String exportInsertTLimitSQL(@RequestParam int pageNum,@RequestParam int pageSize) throws Exception {
        long start = System.currentTimeMillis();
        // 从第1条数据开始生成，生成100w条数据
        // List<TLimit> limits = RandomGenEntityUtil.genTLimit(1,1000000);
        // 从第1000001条数据开始生成，生成100w条数据（这种形式调用不够灵活，尽量简化调用）
        // List<TLimit> limits = RandomGenEntityUtil.genTLimit(1000001,1000000);

        // 采用分页思路进行构建，自定义指定文件编号和单个文件记录条数
        List<TLimit> limits = RandomGenEntityUtil.genTLimit(pageNum,pageSize);
        // 自定义生成文件名称(例如t_limit001)
        String prefix = UUID.randomUUID().toString().substring(0,6) + "-t_limit_";
        String filePath = TARGET_PATH + prefix +  pageNum + ".sql";
        // 导出文件
        tLimitService.exportInsertSQL(limits,filePath);
        long end = System.currentTimeMillis();
        System.out.println("数据导出耗时：" + (end - start) / 1000 + "s");
        return "success";
    }

}
