package com.zzp.provider.aop;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface MethodLog {
//	String id() default "id";
//	boolean argsWhith() default false;

	String tranId() default "";
}
