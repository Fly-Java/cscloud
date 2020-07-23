package com.zzp.provider.aop;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MethodLog {
	String id() default "id";
	boolean argsWhith() default false;
}
