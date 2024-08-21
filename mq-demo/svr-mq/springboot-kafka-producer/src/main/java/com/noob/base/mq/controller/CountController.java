package com.noob.base.mq.controller;

import com.noob.base.mq.model.dto.IncrCountReq;
import com.noob.base.mq.service.CountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/count")
public class CountController {

    @Autowired
    private CountService countService;

    // 传统方式
    @PostMapping(value = "/decoupling",consumes = "application/json;charset=utf-8")
    public ResponseEntity<String> decoupling(@RequestBody IncrCountReq incrCountReq) {
        countService.incrManyTimes(incrCountReq.getNum());
        return ResponseEntity.ok("success");
    }

}
