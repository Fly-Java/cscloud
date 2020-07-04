package com.zzp.provider.controller;

import com.zzp.api.entity.Depart;
import com.zzp.provider.service.DeptService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Component
@RequestMapping("/dept")
public class DeptController {

    @Resource
    private DeptService deptService;

    private Depart findById(Long id){
        Depart depart = deptService.findById(id);
        return depart;
    }

    @RequestMapping("/list")
    private List<Depart> findAllList(){
        List<Depart> list = deptService.findAll();
        return list;
    }

}
