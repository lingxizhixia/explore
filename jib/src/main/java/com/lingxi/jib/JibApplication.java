package com.lingxi.jib;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
public class JibApplication {
    public static void main(String[] args) {
        SpringApplication.run(JibApplication.class, args);
    }
}
