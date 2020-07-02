package com.sendtomoon.sdms;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.sendtomoon.sdms.annotation.MicroService;


public class StartupESADispatchBean implements InitializingBean,ApplicationContextAware{

	private ConcurrentHashMap<String, Properties> microServices = new ConcurrentHashMap<String, Properties>();
	
	private ApplicationContext applicationContext;
	
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		if(applicationContext==null) {
			this.applicationContext=applicationContext;
		}
			
	}
	
	private void toDubbo() {
		for(ConcurrentHashMap.Entry<String, Properties> entry : microServices.entrySet()) {
			ApplicationConfig config = new ApplicationConfig();
			
		}
	}

	public void afterPropertiesSet() throws Exception {
		if(applicationContext==null) {
			this.setApplicationContext(applicationContext);
		}
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

}
