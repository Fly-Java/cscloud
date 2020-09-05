package com.zzp.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName BookDto
 * @Description
 * @Date
 * @Author Administrator
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto implements Serializable {

    /**
     * Id
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String desc;

}
