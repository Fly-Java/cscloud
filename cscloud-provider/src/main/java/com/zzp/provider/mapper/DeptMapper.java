package com.zzp.provider.mapper;

import com.zzp.api.entity.Depart;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeptMapper {

    public Depart findById(Long id);

    public List<Depart> findAll();

}
