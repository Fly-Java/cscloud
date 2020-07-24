package com.zzp.provider.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.CanalEntry.*;
import com.zzp.api.entity.BlogValue;
import com.zzp.provider.mapper.BlogValueMapper;
import com.zzp.provider.service.BlogValueService;
import com.zzp.provider.utils.SnowflakeIdWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName BlogValueServiceImpl
 * @Description
 * @Date
 * @Author Administrator
 **/
@Service
@Slf4j
public class BlogValueServiceImpl implements BlogValueService {

    @Resource
    private BlogValueMapper blogValueMapper;

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
                    JSONObject beforeObject = printColumn(rowData.getBeforeColumnsList());
                    JSONObject afterObject = printColumn(rowData.getAfterColumnsList());
                    blogValue.setId(SnowflakeIdWorker.getNextId());
                    blogValue.setTablesName(entry.getHeader().getTableName());
                    blogValue.setBeforeValue(JSONObject.toJSONString(beforeObject));
                    blogValue.setAfterValue(JSONObject.toJSONString(afterObject));
                    this.insertSelective(blogValue);
                }
            }

        }
    }

    private static JSONObject printColumn(List<CanalEntry.Column> columns) {
        JSONObject object = new JSONObject();
        for (CanalEntry.Column column : columns) {

            object.put(column.getName(), column.getValue());
        }
        return object;
    }

}
