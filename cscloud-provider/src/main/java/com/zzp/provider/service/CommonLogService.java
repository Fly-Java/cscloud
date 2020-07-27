package com.zzp.provider.service;

import com.zzp.api.entity.CommonLog;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName CommonLogService
 * @Description
 * @Date
 * @Author Administrator
 **/
public interface CommonLogService {

    Integer insertCommonLog(CommonLog commonLog);

}
