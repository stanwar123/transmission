package com.hack.TransmissionLineService;

public class PoolPrice {
	
	public String currentpoolPrice;

	public String getCurrentpoolPrice() {
		return currentpoolPrice;
	}

	public void setCurrentpoolPrice(String currentpoolPrice) {
		this.currentpoolPrice = currentpoolPrice;
	}

	@Override
	public String toString() {
		return "PoolPrice [currentpoolPrice=" + currentpoolPrice + "]";
	}

	
}
