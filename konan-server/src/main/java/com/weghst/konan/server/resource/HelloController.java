package com.weghst.konan.server.resource;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Kevin Zou (yong.zou@pilibaba.com)
 */
@RequestMapping("/api")
@RestController
@RefreshScope
public class HelloController {

    @Value("${config.name}")
    private String name = "HELLO";

    @RequestMapping("/")
    public String home() {
        return "Home " + name;
    }

    @RequestMapping("/test2")
    public String test2(HttpServletRequest request) {
        return "Test2";
    }

    @RequestMapping("/test3")
    public String test3() {
        return "Test3";
    }
}
