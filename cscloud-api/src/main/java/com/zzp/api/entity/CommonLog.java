package com.zzp.api.entity;

import lombok.Data;

/**
 * @ClassName CommonLog
 * @Description
 * @Date
 * @Author Administrator
 **/
@Data
public class CommonLog {

    private Long id;

    private String tablesColumn;

    private String tablesName;

    private String beforeValue;

    private String afterValue;

}
