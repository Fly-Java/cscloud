package com.zzp.provider.service;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.zzp.api.entity.BlogValue;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName BlogValueService
 * @Description
 * @Date
 * @Author Administrator
 **/
public interface BlogValueService {

    Integer insertSelective(BlogValue blogValue);

    void printEntry(List<CanalEntry.Entry> entrys);

}
