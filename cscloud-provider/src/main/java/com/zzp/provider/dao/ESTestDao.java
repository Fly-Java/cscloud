package com.zzp.provider.dao;

import com.zzp.api.entity.TestBean;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @ClassName ESTestDao
 * @Description
 * @Date
 * @Author Administrator
 **/
public interface ESTestDao extends CrudRepository<TestBean, Long> {

    List<TestBean> findByName(String name);

    List<TestBean> findByNameOrDesc(String text);

}
