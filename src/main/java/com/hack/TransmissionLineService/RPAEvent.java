package com.hack.TransmissionLineService;

import org.springframework.stereotype.Component;

@Component
public class RPAEvent extends Event{
	public RPAEvent(Action action, String item) {
		super(action, item);

	}
	
	public RPAEvent() {
		super();

	}
}
