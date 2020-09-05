package com.zzp.provider.common;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @ClassName ResponseBean
 * @Description
 * @Date
 * @Author Administrator
 **/
@Data
@AllArgsConstructor
public class ResponseBean {
    /**
     * HTTP状态码
     */
    private Integer code;

    /**
     * 返回信息
     */
    private String msg;

    /**
     * 返回的数据
     */
    private Object data;

}
