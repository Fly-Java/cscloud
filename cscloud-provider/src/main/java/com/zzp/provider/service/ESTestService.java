package com.zzp.provider.service;

import com.zzp.api.entity.TestBean;

import java.util.List;

/**
 * @ClassName ESTestService
 * @Description
 * @Date
 * @Author Administrator
 **/
public interface ESTestService {
    Iterable<TestBean> findAll();

    void save(List<TestBean> list);

    void save(TestBean bean);

    List<TestBean> findByName(String text);

    List<TestBean> findByNameOrDesc(String name,String desc);
}
