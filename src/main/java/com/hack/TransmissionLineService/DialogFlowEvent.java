package com.hack.TransmissionLineService;

import org.springframework.stereotype.Component;

@Component
public class DialogFlowEvent extends Event{

	public DialogFlowEvent(Action action, String item) {
		super(action, item);

	}
	
	public DialogFlowEvent() {
		super();

	}
	
}
