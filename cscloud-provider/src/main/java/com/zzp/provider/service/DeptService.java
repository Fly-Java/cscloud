package com.zzp.provider.service;

import com.zzp.api.entity.Depart;

import java.util.List;

public interface DeptService {

    public Depart findById(Long id);

    public List<Depart> findAll();

}
