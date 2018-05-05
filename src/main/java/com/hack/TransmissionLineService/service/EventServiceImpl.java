package com.hack.TransmissionLineService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hack.TransmissionLineService.Action;
import com.hack.TransmissionLineService.DialogFlowEvent;
import com.hack.TransmissionLineService.RPAEvent;
@Service
public class EventServiceImpl implements EventService{

	@Autowired
	DialogFlowEvent dialogFlowEvent;
	
	@Autowired
	RPAEvent rpaEvent;
	
	@Override
	public DialogFlowEvent getDialogFlowEvent() {
		DialogFlowEvent newEvent = null;
		if(dialogFlowEvent.getAction()!=null){
			newEvent = new DialogFlowEvent(dialogFlowEvent.getAction(),dialogFlowEvent.getItem() );
			dialogFlowEvent.setAction(Action.NO_PENDING_ACTION);
			dialogFlowEvent.setItem("TestLine1");
		}else{
			newEvent = new DialogFlowEvent(Action.NO_PENDING_ACTION,"TestLine1");
		}
		return newEvent;
	}

	@Override
	public void addDialogFlowEvent(Action action, String item) {
		dialogFlowEvent = new DialogFlowEvent();
		dialogFlowEvent.setAction(action);
		dialogFlowEvent.setItem(item);
	}
	
	@Override
	public void addDialogFlowEvent(DialogFlowEvent event) {
		this.dialogFlowEvent = event;
	}
	
	
	@Override
	public RPAEvent getRPAEvent() {
		RPAEvent newEvent = null;
		if(rpaEvent.getAction()!=null){
			newEvent = new RPAEvent(rpaEvent.getAction(),rpaEvent.getItem() );
			rpaEvent.setAction(Action.NO_PENDING_ACTION);
			rpaEvent.setItem("TestLine1");
		}else{
			newEvent = new RPAEvent(Action.NO_PENDING_ACTION,"TestLine1");
		}
		return newEvent;
	}

	@Override
	public void addRPAEvent(Action action, String item) {
		rpaEvent  = new RPAEvent();
		rpaEvent.setAction(action);
		rpaEvent.setItem(item);
	}
	
	@Override
	public void addRPAEvent(RPAEvent event) {
		this.rpaEvent = event;
	}

}
