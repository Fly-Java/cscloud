package com.zzp.api.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Depart implements Serializable {

    private String id;
    private String deptNo;
    private String deptName;
    private String tranId;
}
