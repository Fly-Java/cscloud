package com.zzp.consumer.controller;

import com.zzp.api.entity.Depart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class ConsumerController {

    private static final String REST_URL_PREFIX = "http://localhost:8001";

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "consumer/dept")
    public List<Depart> getDepartList(){
        return (List<Depart>) restTemplate.getForEntity("REST_URL_PREFIX" + "/dept/list", Depart.class);
    }


}
