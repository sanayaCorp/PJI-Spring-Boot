package com.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SwaggerController {
    public static final String SWAGGER_START_PAGE = "redirect:/swagger-ui.html";

    @RequestMapping(value = "/swagger")
    public String index() {
        return SWAGGER_START_PAGE;
    }
}
