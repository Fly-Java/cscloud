package com.zzp.provider.mapper;

import com.zzp.api.entity.Depart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DeptMapper {

    Depart findById(@Param("id") String id);

    Integer updateDeptById(@Param("id") String id, @Param("deptNo") String deptNo, @Param("deptName") String deptName, @Param("tranId") String tranId);

    List<Depart> findAll();

}
