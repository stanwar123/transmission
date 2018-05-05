package com.hack.TransmissionLineService;

import org.springframework.stereotype.Component;

@Component
public class Event {
	
	private Action action;
	
	private String item;

	public Event() {
		super();
	}

	public Event(Action action, String item) {
		super();
		this.action = action;
		this.item = item;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}



}
