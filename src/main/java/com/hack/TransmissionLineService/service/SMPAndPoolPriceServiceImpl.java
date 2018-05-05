package com.hack.TransmissionLineService.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hack.TransmissionLineService.PoolPrice;
import com.hack.TransmissionLineService.SMP;
@Service
public class SMPAndPoolPriceServiceImpl implements SMPAndPoolPriceService{

	@Autowired
	SMP smp;
	
	PoolPrice poolprice = new PoolPrice();
	
	
	@Override
	public SMP getSMP() {
		return smp;
	}

	@Override
	public SMP setCurrentSMP(BigDecimal price) {
		smp.setCurrentSMP(price);
		return smp;
	}
	
	@Override
	public PoolPrice getPoolPrice() {
		return poolprice;
	}

	@Override
	public PoolPrice setCurrentPoolPrice(String price) {
		poolprice.setCurrentpoolPrice(price);
		return poolprice;
	}

	public SMP getSmp() {
		return smp;
	}

	public void setSmp(SMP smp) {
		this.smp = smp;
	}

	public PoolPrice getPoolprice() {
		return poolprice;
	}

	public void setPoolprice(PoolPrice poolprice) {
		this.poolprice = poolprice;
	}
	
	
	
}
