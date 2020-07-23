package com.zzp.provider.service;

import com.zzp.api.entity.Depart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeptService {

    Depart findById(String id);

    Depart updateDeptById(String id, String deptNo, String deptName, String tranId);

    List<Depart> findAll();

}
