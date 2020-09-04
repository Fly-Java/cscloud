package com.zzp.provider.service.impl;

import com.zzp.api.entity.TestBean;
import com.zzp.provider.service.ESTestService;

import java.util.List;

/**
 * @ClassName ESTestServiceImpl
 * @Description
 * @Date
 * @Author Administrator
 **/
public class ESTestServiceImpl implements ESTestService {
    @Override
    public Iterable<TestBean> findAll() {
        return null;
    }

    @Override
    public void save(List<TestBean> list) {

    }

    @Override
    public void save(TestBean bean) {

    }

    @Override
    public List<TestBean> findByName(String text) {
        return null;
    }

    @Override
    public List<TestBean> findByNameOrDesc(String name, String desc) {
        return null;
    }
}
