package com.zzp.provider.aop;

import com.alibaba.fastjson.JSONObject;
import com.zzp.api.entity.Depart;
import com.zzp.provider.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.Objects;

@Aspect
@Component
@Slf4j
public class MethodLogAspect {

    @Autowired
    private DeptService deptService;

//    //定义切点
//    @Pointcut("@annotation(com.zzp.provider.aop.MethodLog)")
//    public void doAspect() {
//    }

    @Around("@annotation(methodLog)")
    public Object doAround(ProceedingJoinPoint pjd, MethodLog methodLog) {
        Object result = null;

        try {
            Class clazz = ((MethodSignature) pjd.getSignature()).getReturnType();

            // 获取request对象
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            // 从request对象中获取参数
            StringBuffer sb = new StringBuffer();
            Enumeration enu = request.getParameterNames();
            while (enu.hasMoreElements()) {
                String paraName = (String) enu.nextElement();
                sb.append(paraName + ": " + request.getParameter(paraName) + ",");
            }
            if (clazz.equals(Depart.class)) {
               Object[] args = pjd.getArgs();
                for (Object obj : args) {
                    if (Objects.nonNull(obj)){
                        JSONObject object = (JSONObject) JSONObject.toJSON(obj);
                        log.info("环绕通知");
                        result = deptService.findById(object.get("id").toString());
                    }
                }
                JSONObject object = (JSONObject) JSONObject.toJSON(args);
            }
        } catch (Throwable e) {
            System.out.println("异常通知");
        }
        return result;

    }

}
