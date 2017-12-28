package com.lingxi.explore.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class DnsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DnsApplication.class, args);
	}
}
