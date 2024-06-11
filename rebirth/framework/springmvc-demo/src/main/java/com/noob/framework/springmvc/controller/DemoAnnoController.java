package com.noob.framework.springmvc.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/anno")
public class DemoAnnoController {

    @RequestMapping(value = "/index1", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String index1(HttpServletRequest request) {
        return "index1 url:" + request.getRequestURI() + " can access";
    }

    @RequestMapping(value = "/index2", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
    public @ResponseBody String index2(HttpServletRequest request) {
        return "index2 url:" + request.getRequestURI() + " can access";
    }

    @RequestMapping(value = "/param", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String param(@RequestParam(defaultValue = "1") int id,@RequestParam(required = false) String name) {
        return "get name success: id :" + id + " name : " + name;
    }

    @RequestMapping(value = "/pathVar/{str}/{otherStr}", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String pathVar(@PathVariable String str, @PathVariable(value = "otherStr") String name) {
        return "get name success: str :" + str + " name : " + name;
    }

    @RequestMapping(value = "/body", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String pathVar(@RequestBody String name) {
        return "get name success: name : " + name;
    }

}
