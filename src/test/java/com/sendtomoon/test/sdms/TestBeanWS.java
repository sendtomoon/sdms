package com.sendtomoon.test.sdms;

import org.springframework.stereotype.Component;

import com.sendtomoon.sdms.annotation.MicroService;

@Component
public class TestBeanWS{

	
	@MicroService(name="test.test111")
	public String testMicroService() {
		return "successful";
	}
}
