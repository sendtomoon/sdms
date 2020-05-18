package com.sendtomoon.sdms.event;

import org.springframework.context.ApplicationEvent;

public class MicroServiceEvent extends ApplicationEvent {

	private static final long serialVersionUID = -6366926652472345069L;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String name;

	public MicroServiceEvent(Object source, String name) {
		super(source);
		this.name = name;
	}

	public MicroServiceEvent(Object source) {
		super(source);
	}

}
