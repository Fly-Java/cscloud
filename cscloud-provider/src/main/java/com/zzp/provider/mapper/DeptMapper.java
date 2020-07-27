package com.zzp.provider.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zzp.api.entity.Depart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DeptMapper extends BaseMapper<Depart> {

    Depart findById(@Param("id") String id);

    Integer updateDeptById(@Param("depart") Depart depart);


}
