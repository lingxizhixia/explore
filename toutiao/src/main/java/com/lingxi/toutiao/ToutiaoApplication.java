package com.lingxi.toutiao;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@NacosPropertySource(dataId = "com.cedarsv.plus.openapi.properties", autoRefreshed = true)
public class ToutiaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToutiaoApplication.class, args);
    }
}
