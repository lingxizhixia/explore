package com.lingxi.explore.eureka;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaApplicaton {
    public static void main(String[] args) {
        new SpringApplicationBuilder(EurekaApplicaton.class).web(true).run(args);
    }
}
