package com.productpilothome.commodity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Heather
 * @version 1.0
 */
@EnableDiscoveryClient
@SpringBootApplication
public class PphCommodityApplication {
    public static void main(String[] args) {
        SpringApplication.run(PphCommodityApplication.class, args);
    }
}
