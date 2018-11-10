package com.tongdou.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class HelloController {
    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @RequestMapping("index")
    public String index() {
        return "你好";
    }

    /*http://localhost:8080/getParam/xcvxcv/dddd*/
    @RequestMapping("getParam/{id}/{name}")
    public Map getParam(@PathVariable String id, @PathVariable String name) {
        Map map = new HashMap();
        map.put("id", id);
        map.put("name", name);

        return map;
    }

    /*http://localhost:8080/getRequestParam?id=123123&name=aaa*/
    @RequestMapping("getRequestParam")
    public Map getRequestParam(@RequestParam String id, @RequestParam String name) {
        Map map = new HashMap();
        map.put("id", id);
        map.put("name", name);

        logger.info(map.toString());
        return map;
    }

}
