package com.zzp.api.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class BlogValue implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String tablesName;

    private String beforeValue;

    private String afterValue;

}