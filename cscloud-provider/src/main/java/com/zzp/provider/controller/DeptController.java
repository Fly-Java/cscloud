package com.zzp.provider.controller;

import com.zzp.api.entity.Depart;
import com.zzp.provider.aop.MethodLog;
import com.zzp.provider.service.DeptService;
import com.zzp.provider.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @RequestMapping("/findDepart")
    private Depart findById(String id){
        Depart depart = deptService.findById(id);
        return depart;
    }

    @GetMapping("/hello")
    public Depart hello(){
        Depart depart = new Depart();
        depart.setId("1");
        depart.setDeptName("技术部");
        depart.setDeptNo("101");
        depart = deptService.hello(depart,"1");
        depart.setId("10");
        depart = deptService.hello(depart,"2");
        return depart;
    }

    @PostMapping("/updateDeptById")
    private Depart updateDeptById(){
        return deptService.updateDeptById("1", "123", "456", UUIDUtils.getUUID());
    }

    @RequestMapping("/list")
    private List<Depart> findAllList(){
        List<Depart> list = deptService.findAll();
        return list;
    }

}
