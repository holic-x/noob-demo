package com.noob.dockerdemo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DockerController {

    // http://127.0.0.1:8081/index
    @RequestMapping("/index")
    public String index() {
        return "hello Docker";
    }

}
