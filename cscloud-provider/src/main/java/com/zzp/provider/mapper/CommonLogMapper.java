package com.zzp.provider.mapper;

import com.zzp.api.entity.CommonLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName CommonLogMapper
 * @Description
 * @Date
 * @Author Administrator
 **/
@Mapper
public interface CommonLogMapper {

    Integer insertCommonLog(@Param("log")CommonLog commonLog);

}
