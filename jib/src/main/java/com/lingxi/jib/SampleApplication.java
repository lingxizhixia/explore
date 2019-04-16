package com.lingxi.jib;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sample")
public class SampleApplication {

    @RequestMapping("/ping")
    public String echo() {
        return "pang";
    }
}
