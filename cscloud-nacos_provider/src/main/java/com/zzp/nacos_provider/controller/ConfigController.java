package com.zzp.nacos_provider.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ConfigController {

    @Autowired
    private ConfigurableApplicationContext applicationContext;

    @Value("${common.name}")
    private String name;

    @GetMapping("/getConfigs")
    public String getConfigs(){
        log.info("name:" + name);
        String name = applicationContext.getEnvironment().getProperty("common.name");
        String address = applicationContext.getEnvironment().getProperty("common.address");
        return name + address;
    }
}
