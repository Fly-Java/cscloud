package com.zzp.provider.utils;

import java.util.UUID;

/**
 * @ClassName UUIDUtils
 * @Description
 * @Date
 * @Author Administrator
 **/
public class UUIDUtils {

    public static String getUUID(){
        String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
        return uuid;
    }

}
