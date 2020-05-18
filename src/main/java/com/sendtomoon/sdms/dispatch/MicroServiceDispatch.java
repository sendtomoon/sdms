package com.sendtomoon.sdms.dispatch;

import java.lang.reflect.Method;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.sendtomoon.sdms.annotation.MicroService;

/**
 * 微服务注解处理引导
 *
 */
public class MicroServiceDispatch implements ApplicationListener<ContextRefreshedEvent> {
	
	private ConcurrentHashMap<String, Properties> microServices = new ConcurrentHashMap<String, Properties>();

	private void resloveMicroService(ApplicationContext ac) {
		String[] names = ac.getBeanDefinitionNames();
		for (String name : names) {
			Class<?> beanClass = ac.getType(name);
			Method[] ms = beanClass.getMethods();
			for (Method m : ms) {
				MicroService an = m.getAnnotation(MicroService.class);
				if (an == null) {
					continue;
				}
				Properties p = new Properties();
				p.setProperty("name", an.name());
			}
		}
	}

	public void onApplicationEvent(ContextRefreshedEvent event) {
		ApplicationContext ac = event.getApplicationContext();
		this.resloveMicroService(ac);
	}

}
