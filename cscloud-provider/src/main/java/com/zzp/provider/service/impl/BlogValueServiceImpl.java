package com.zzp.provider.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.CanalEntry.EntryType;
import com.alibaba.otter.canal.protocol.CanalEntry.EventType;
import com.alibaba.otter.canal.protocol.CanalEntry.RowChange;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zzp.api.entity.BlogValue;
import com.zzp.api.entity.CommonLog;
import com.zzp.api.entity.Depart;
import com.zzp.provider.mapper.BlogValueMapper;
import com.zzp.provider.service.BlogValueService;
import com.zzp.provider.service.CommonLogService;
import com.zzp.provider.utils.SnowflakeIdWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName BlogValueServiceImpl
 * @Description
 * @Date
 * @Author Administrator
 **/
@Service
@Slf4j
public class BlogValueServiceImpl extends ServiceImpl<BlogValueMapper, BlogValue> implements BlogValueService {

    @Resource
    private BlogValueMapper blogValueMapper;
    @Resource
    private CommonLogService commonLogService;

    @Override
    public Integer insertSelective(BlogValue blogValue) {
        return blogValueMapper.insertSelective(blogValue);
    }


    @Override
    public void printEntry(List<CanalEntry.Entry> entrys) {
        for (CanalEntry.Entry entry : entrys) {
            if (entry.getEntryType() == EntryType.TRANSACTIONBEGIN || entry.getEntryType() == EntryType.TRANSACTIONEND) {
                continue;
            }

            RowChange rowChage = null;
            try {
                rowChage = RowChange.parseFrom(entry.getStoreValue());
            } catch (Exception e) {
                throw new RuntimeException("ERROR ## parser of eromanga-event has an error , data:" + entry.toString(),
                        e);
            }

            CanalEntry.EventType eventType = rowChage.getEventType();
            System.out.println(String.format("================&gt; binlog[%s:%s] , name[%s,%s] , eventType : %s",
                    entry.getHeader().getLogfileName(), entry.getHeader().getLogfileOffset(),
                    entry.getHeader().getSchemaName(), entry.getHeader().getTableName(),
                    eventType));

            for (CanalEntry.RowData rowData : rowChage.getRowDatasList()) {
                if (eventType == EventType.DELETE) {
                    printColumn(rowData.getBeforeColumnsList());
                } else if (eventType == CanalEntry.EventType.INSERT) {
                    printColumn(rowData.getAfterColumnsList());
                } else {
                    BlogValue blogValue = new BlogValue();
                    List<JSONObject> beforeObject = printColumn(rowData.getBeforeColumnsList());
                    List<JSONObject> afterObject = printColumn(rowData.getAfterColumnsList());
                    blogValue.setId(SnowflakeIdWorker.getNextId());
                    blogValue.setTablesName(entry.getHeader().getTableName());
                    blogValue.setBeforeValue(JSONObject.toJSONString(beforeObject));
                    blogValue.setAfterValue(JSONObject.toJSONString(afterObject));
                    baseMapper.insert(blogValue);
                }
            }

        }
    }

    @Override
    public BlogValue selectBlogValueByTandId(String tranId) {
        return blogValueMapper.selectBlogValueByTandId(tranId);
    }

    @Override
    public void initBlogValue(String tranId) {
        BlogValue blogValue = this.selectBlogValueByTandId(tranId);
        if (Objects.nonNull(blogValue)) {
            String beforeValue = blogValue.getBeforeValue();
            String afterValue = blogValue.getAfterValue();
            String tableName = blogValue.getTablesName();
            List<Depart> beforeDepart = JSONArray.parseArray(beforeValue, Depart.class);
            List<Depart> afterDepart = JSONArray.parseArray(afterValue, Depart.class);
            List<CommonLog> logList = compareFields(beforeDepart.get(0), afterDepart.get(0));

            for (CommonLog commonLog : logList) {
                commonLog.setTablesName(tableName);
                commonLogService.insertCommonLog(commonLog);
            }

        }
    }

    private static List<JSONObject> printColumn(List<CanalEntry.Column> columns) {
        JSONObject object = new JSONObject();
        for (CanalEntry.Column column : columns) {
            object.put(column.getName(), column.getValue());
        }
        List<JSONObject> jsonObjectList = new ArrayList<>();
        jsonObjectList.add(object);
        return jsonObjectList;
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
                    for (Field afterField : afterFields) {
                        afterField.setAccessible(true);
                        Object afterValue = afterField.get(obj2);
                        String afterFieldName = afterField.getName();
                        if (afterFieldName.equals(beforeFieldName)) {
                            if (!afterValue.equals(beforeValue)) {
                                CommonLog commonLog = new CommonLog();
                                commonLog.setId(SnowflakeIdWorker.getNextId());
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


