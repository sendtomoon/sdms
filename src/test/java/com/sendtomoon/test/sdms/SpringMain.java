package com.sendtomoon.test.sdms;

import java.lang.reflect.Method;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sendtomoon.sdms.annotation.MicroService;

public class SpringMain {

	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring.xml");
		String[] names = ac.getBeanDefinitionNames();
		for (String name : names) {
			Class<?> beanClass = ac.getType(name);
			Method[] ms = beanClass.getMethods();
			for(Method m : ms) {
				MicroService an = m.getAnnotation(MicroService.class);
				if(an==null) {
					continue;
				}
				System.err.println(an.name());
			}
		}
		
	}

}
