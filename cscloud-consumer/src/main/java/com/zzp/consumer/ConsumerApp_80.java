package com.zzp.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ConsumerApp_80 {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApp_80.class, args);
    }
}
