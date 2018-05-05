package com.hack.TransmissionLineService.web;
/*
 * Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */



import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hack.TransmissionLineService.Action;
import com.hack.TransmissionLineService.DialogFlowEvent;
import com.hack.TransmissionLineService.Event;
import com.hack.TransmissionLineService.PoolPrice;
import com.hack.TransmissionLineService.RPAEvent;
import com.hack.TransmissionLineService.SMP;
import com.hack.TransmissionLineService.domain.Generation;
import com.hack.TransmissionLineService.domain.TransmissionLine;
import com.hack.TransmissionLineService.service.EventService;
import com.hack.TransmissionLineService.service.SMPAndPoolPriceService;
import com.hack.TransmissionLineService.service.TransmissionLineService;

@RestController
public class SampleController {
	
	@Autowired
	EventService eventService;
	
	@Autowired
	SMPAndPoolPriceService smpAndPoolPriceService;
	
	//Just gets the line name
	@Autowired
	private TransmissionLineService transmissionLineService;
	@RequestMapping(value="/lines/{line}", method=RequestMethod.GET)
	@ResponseBody
	@Produces({MediaType.APPLICATION_JSON})
	@Transactional(readOnly = true)
	public TransmissionLine transmissionLineName(@PathVariable String line) {
		//TestLine1
		return transmissionLineService.getTransmissionLine(line.toUpperCase());
	}

	//get the generation levels
	@RequestMapping(value="/lines/{line}/generations", method=RequestMethod.GET)
	@ResponseBody
	@Transactional(readOnly = true)
	public Set<Generation> generationLevelsforLine(@PathVariable String line) {
		//TestLine1
		//return transmissionLineService.getTransmissionLine(line).getGenerations().stream().map(generation -> generation.toString()).collect(Collectors.toSet());
		return transmissionLineService.getTransmissionLine(line.toUpperCase()).getGenerations();
	}

	//get the generating unit level
	@RequestMapping(value="/lines/{line}/{generationUnit}", method=RequestMethod.GET)
	@ResponseBody
	@Transactional(readOnly = true)
	public int generationLevelsforLineandGenUnit(@PathVariable String line, @PathVariable String generationUnit) {
		Optional<Generation> gen = transmissionLineService.getTransmissionLine(line.toUpperCase()).getGenerations().stream().filter(unit -> unit.getName().equals(generationUnit)).findFirst();
		if(gen.isPresent()){
			return gen.get().getGenerationLevel().intValue();
		} else {
			return BigDecimal.ZERO.intValue();
		}
	}
	
	//update the line capacity
	@RequestMapping(value = "/lines/{line}/update/{capacity}", method = RequestMethod.POST)
    @Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public @ResponseBody ResponseEntity<TransmissionLine> update(@PathVariable String line, @PathVariable String capacity) {
		System.out.println("Inside updateCapacityPage**** ");
		transmissionLineService.updateCapacity(line.toUpperCase(), Integer.valueOf(capacity));
	    // TODO: call persistence layer to update
	    return new ResponseEntity<TransmissionLine>(transmissionLineService.getTransmissionLine(line.toUpperCase()), HttpStatus.OK);
	}
	
	//update the generation level
	@RequestMapping(value = "/lines/generation/update", method = RequestMethod.POST)
    @Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public @ResponseBody ResponseEntity<TransmissionLine> update(String line, Generation generation) {
		System.out.println("Inside updateCapacityPage**** ");
		transmissionLineService.updateLevelGeneration(generation.getName(), line.toUpperCase(), generation.getGenerationLevel());
	    // TODO: call persistence layer to update
	    return new ResponseEntity<TransmissionLine>(transmissionLineService.getTransmissionLine(line), HttpStatus.OK);
	}
	
	//get action
	@RequestMapping(value="/action/dialogflow", method=RequestMethod.GET)
	@Produces({MediaType.APPLICATION_JSON})
	@Transactional(readOnly = true)
	public DialogFlowEvent getAction() {
		return eventService.getDialogFlowEvent();
	}
	
	//post action
	@RequestMapping(value = "/action/dialogflow/fire/{action}", method = RequestMethod.GET)
	public @ResponseBody void postDialoFlowAction(@PathVariable String action) {
		eventService.addDialogFlowEvent(new DialogFlowEvent(Action.valueOf(action),""));
	}
	
	//get action
	@RequestMapping(value="/action/rpa", method=RequestMethod.GET)
	@Produces({MediaType.APPLICATION_JSON})
	@Transactional(readOnly = true)
	public RPAEvent getRPAAction() {
		return eventService.getRPAEvent();
	}
	
	//post action
	@RequestMapping(value = "/action/rpa/fire/{action}", method = RequestMethod.GET)
	public @ResponseBody void postRPAAction(@PathVariable String action) {
		eventService.addRPAEvent(new RPAEvent(Action.valueOf(action),""));
	}

	@RequestMapping(value = "/action/cheklines", method = RequestMethod.GET)
	public @ResponseBody String checklines() {
		return transmissionLineService.checkAllLines();
	}
	
	//get action for SMP
	@RequestMapping(value="/smp", method=RequestMethod.GET)
	@Produces({MediaType.APPLICATION_JSON})
	@Transactional(readOnly = true)
	public SMP getSMP() {
		return smpAndPoolPriceService.getSMP();
	}
	
	//post action for SMP
	@RequestMapping(value = "/smp/update", method = RequestMethod.POST)
    @Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public @ResponseBody void postAction(SMP smp) {
		smpAndPoolPriceService.setCurrentSMP(smp.getCurrentSMP());
	}
	
	//get action for pool price
	@RequestMapping(value="/rpa/fetchPoolprice", method=RequestMethod.GET)
	@Produces({MediaType.APPLICATION_JSON})
	@Transactional(readOnly = true)
	public PoolPrice fetchPoolPriceFromRPA() {
		eventService.addRPAEvent(new RPAEvent(Action.GET_POOL_PRICE,""));
		long endTimeMillis = getEndMillis();
		while(smpAndPoolPriceService.getPoolPrice().getCurrentpoolPrice() ==null && System.currentTimeMillis() < endTimeMillis){
			System.out.println("Pool In Loop Price**** "+smpAndPoolPriceService.getPoolPrice().getCurrentpoolPrice());
		}
		PoolPrice poolPrice = smpAndPoolPriceService.getPoolPrice();
		smpAndPoolPriceService.setPoolprice(new PoolPrice());
		return poolPrice;
	}
	
	//get action for pool price
	@RequestMapping(value="/poolprice", method=RequestMethod.GET)
	@Produces({MediaType.APPLICATION_JSON})
	@Transactional(readOnly = true)
	public PoolPrice getPoolPrice() {
		PoolPrice poolPrice = smpAndPoolPriceService.getPoolPrice();
		return poolPrice;
	}
	
	//post action for pool price
	@RequestMapping(value = "/poolprice/update", method = RequestMethod.POST)
    @Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public @ResponseBody PoolPrice postAction(PoolPrice poolPrice) {
		System.out.println("Pool Price**** "+poolPrice);
		System.out.println("Pool Price value **** "+poolPrice.currentpoolPrice);
		smpAndPoolPriceService.setCurrentPoolPrice(poolPrice.currentpoolPrice);
		return smpAndPoolPriceService.getPoolPrice();
	}
	
	private long getEndMillis(){
		long endTimeMillis = System.currentTimeMillis() + 4000;
		return endTimeMillis;
	}
	
}
