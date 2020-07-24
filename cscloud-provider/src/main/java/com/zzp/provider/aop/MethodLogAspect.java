package com.zzp.provider.aop;

import com.zzp.api.entity.Depart;
import com.zzp.provider.config.JmsConfig;
import com.zzp.provider.rocketMQ.Provider;
import com.zzp.provider.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
@Slf4j
public class MethodLogAspect {

    @Autowired
    private DeptService deptService;

    @Autowired
    private Provider provider;

//    //定义切点
//    @Pointcut("@annotation(com.zzp.provider.aop.MethodLog)")
//    public void doAspect() {
//    }

    @After("@annotation(methodLog)")
    public Object doAround(JoinPoint pjd, MethodLog methodLog) {
        Object result = null;

        try {
            Class clazz = ((MethodSignature) pjd.getSignature()).getReturnType();
            Object[] paramValues = pjd.getArgs();
            String[] paramNames = ((CodeSignature) pjd.getSignature()).getParameterNames();
            result = ((MethodSignature) pjd.getSignature()).getMethod();
            Map<String, Object> paramMap = new HashMap<>();


//            String tranId = ((Depart) paramValues).getTranId();
            for (int i = 0; i < paramNames.length; i++) {
                paramMap.put(paramNames[i], paramValues[i]);
            }
            Message message = new Message(JmsConfig.TOPIC, "testtag", paramMap.get("depart").toString().getBytes());
            //发送
            SendResult sendResult = provider.getProducer().send(message);
        } catch (Throwable e) {
            System.out.println("异常通知");
        }
        return result;

    }

}
