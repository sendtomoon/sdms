package com.sendtomoon.sdms.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MicroService {

	/**dubbo服务名*/
	String name() default "";
	
	/**dubbo服务名*/
	String value() default "";
	
	/**服务分组名*/
	String group() default "";
	
	/**权重*/
    int weight() default 0;
	
}
