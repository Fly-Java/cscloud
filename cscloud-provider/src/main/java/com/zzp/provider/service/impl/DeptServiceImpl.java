package com.zzp.provider.service.impl;

import com.zzp.api.entity.Depart;
import com.zzp.provider.aop.MethodLog;
import com.zzp.provider.mapper.DeptMapper;
import com.zzp.provider.service.DeptService;
import com.zzp.provider.utils.UUIDUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class DeptServiceImpl implements DeptService {

    @Resource
    private DeptMapper deptMapper;

    @Override
    @MethodLog
    public Depart findById(String id) {
        return deptMapper.findById(id);
    }

    @Override
    public Depart hello(Depart depart,String tranId) {
        return deptMapper.findById(depart.getId());
    }

    @Override
    @MethodLog()
    @PostConstruct
    // 项目执行一次
    public Depart updateDeptById(String id, String deptNo, String deptName, String tranId) {
        log.info("变更前");
        Integer integer = deptMapper.updateDeptById(id, deptNo, deptName, tranId);
        log.info("变更后");
        return deptMapper.findById(id);
    }


    @Override
    public List<Depart> findAll() {
        return deptMapper.findAll();
    }
}
