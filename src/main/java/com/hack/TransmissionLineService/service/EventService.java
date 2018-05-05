package com.hack.TransmissionLineService.service;

import com.hack.TransmissionLineService.Action;
import com.hack.TransmissionLineService.DialogFlowEvent;
import com.hack.TransmissionLineService.RPAEvent;

public interface EventService {

	DialogFlowEvent getDialogFlowEvent();

	void addDialogFlowEvent(Action action, String item);

	void addDialogFlowEvent(DialogFlowEvent event);

	RPAEvent getRPAEvent();

	void addRPAEvent(Action action, String item);

	void addRPAEvent(RPAEvent event);
}
