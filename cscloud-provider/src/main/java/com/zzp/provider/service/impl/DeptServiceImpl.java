package com.zzp.provider.service.impl;

import com.zzp.api.entity.Depart;
import com.zzp.provider.mapper.DeptMapper;
import com.zzp.provider.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Resource
    private DeptMapper deptMapper;

    @Override
    public Depart findById(Long id) {
        return deptMapper.findById(id);
    }

    @Override
    public List<Depart> findAll() {
        return deptMapper.findAll();
    }
}
