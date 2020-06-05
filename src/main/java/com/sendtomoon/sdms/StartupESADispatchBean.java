package com.sendtomoon.sdms;

import java.lang.reflect.Method;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.sendtomoon.sdms.annotation.MicroService;

public class StartupESADispatchBean implements ApplicationContextAware{

	private ConcurrentHashMap<String, Properties> microServices = new ConcurrentHashMap<String, Properties>();
	
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
			String[] names = applicationContext.getBeanDefinitionNames();
			for (String name : names) {
				Class<?> beanClass = applicationContext.getType(name);
				Method[] ms = beanClass.getMethods();
				for (Method m : ms) {
					MicroService an = m.getAnnotation(MicroService.class);
					if (an == null) {
						continue;
					}
					Properties p = new Properties();
					p.setProperty("name", an.name());
					p.setProperty("value", an.value());
					p.setProperty("group", an.group());
					p.setProperty("weight", String.valueOf(an.weight()));
					microServices.put(an.name(), p);
				}
			}
	}
	
	private void toDubbo() {
		for(Set<String> key : microServices.entrySet()) {
			
		}
	}

}
