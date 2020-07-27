package com.zzp.provider.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zzp.api.entity.CommonLog;
import com.zzp.provider.mapper.CommonLogMapper;
import com.zzp.provider.service.CommonLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName CommonLogServiceImpl
 * @Description
 * @Date
 * @Author Administrator
 **/
@Service
public class CommonLogServiceImpl extends ServiceImpl<CommonLogMapper, CommonLog> implements CommonLogService {

    @Resource
    private CommonLogMapper commonLogMapper;

    @Override
    public Integer insertCommonLog(CommonLog commonLog) {
        return commonLogMapper.insertCommonLog(commonLog);
    }
}
