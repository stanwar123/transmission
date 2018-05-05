package com.hack.TransmissionLineService;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Component
public class SMP {
	
	private BigDecimal currentSMP;

	public BigDecimal getCurrentSMP() {
		return currentSMP;
	}

	public void setCurrentSMP(BigDecimal currentSMP) {
		this.currentSMP = currentSMP;
	}
	
	
}
