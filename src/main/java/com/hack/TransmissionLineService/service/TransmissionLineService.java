package com.hack.TransmissionLineService.service;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import com.hack.TransmissionLineService.domain.Generation;
import com.hack.TransmissionLineService.domain.TransmissionLine;

public interface TransmissionLineService {
	
	public TransmissionLine getTransmissionLine(String name);
	
	Set<Generation> getGenerationLevels(String transmissionLineName);
	
	public List<TransmissionLine> getAllTransmissionLines();
	
	public TransmissionLine updateCapacity(String line, int newCapacity);

	Generation getGenerationByName(String generationName, String transmissionLineName);

	TransmissionLine updateLevelGeneration(String generationName, String transmissionLineName, BigDecimal capacity);

	String checkAllLines();
}
