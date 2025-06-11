package com.productpilothome.pphservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class PphServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(PphServiceApplication.class, args);
    }

}
