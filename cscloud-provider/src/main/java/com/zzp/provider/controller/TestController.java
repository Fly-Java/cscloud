package com.zzp.provider.controller;

import com.zzp.api.entity.CommonLog;
import com.zzp.api.entity.Depart;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName TestController
 * @Description
 * @Date
 * @Author Administrator
 **/
@Slf4j
public class TestController {

    public static void main(String[] args) {
        Depart depart = new Depart();
        depart.setId("");
        depart.setDeptNo("123");
        depart.setDeptName("java");
        depart.setTranId("84c0314d995c4a3cb8eee825b4ac8955");

        Depart depart1 = new Depart();
        depart1.setId("");
        depart1.setDeptNo("123");
        depart1.setDeptName("运维");
        depart1.setTranId("84c0314d995c4a3cb8eee825b4ac8955");

        List<CommonLog> logList = compareFields(depart, depart1);
        log.info("logList");


    }

    public static List<CommonLog> compareFields(Object obj1, Object obj2) {

        List<CommonLog> logList = new ArrayList<>();
        try {
            if (obj1.getClass() == obj2.getClass()) {
                Field[] beforeFields = obj1.getClass().getDeclaredFields();
                Field[] afterFields = obj2.getClass().getDeclaredFields();
                for (Field beforeField : beforeFields) {
                    beforeField.setAccessible(true);
                    Object beforeValue = beforeField.get(obj1);
                    String beforeFieldName = beforeField.getName();
                    for (Field afterField :afterFields) {
                        afterField.setAccessible(true);
                        Object afterValue = afterField.get(obj2);
                        String afterFieldName = afterField.getName();
                        if (afterFieldName.equals(beforeFieldName)){
                            if (!afterValue.equals(beforeValue)){
                                CommonLog commonLog = new CommonLog();
                                commonLog.setTablesColumn(afterFieldName);
                                commonLog.setBeforeValue(beforeValue.toString());
                                commonLog.setAfterValue(afterValue.toString());
                                logList.add(commonLog);
                            }
                        }
                    }
                }
            }
        } catch (IllegalAccessException e) {
            log.info("对象比较异常:", e);
        }
        return logList;
    }

}
