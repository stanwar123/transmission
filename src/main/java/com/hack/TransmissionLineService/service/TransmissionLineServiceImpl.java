package com.hack.TransmissionLineService.service;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hack.TransmissionLineService.dao.TransmissionLineRepo;
import com.hack.TransmissionLineService.domain.Generation;
import com.hack.TransmissionLineService.domain.TransmissionLine;
@Service
@Transactional
public class TransmissionLineServiceImpl implements TransmissionLineService {

	@Autowired
	TransmissionLineRepo  transmissionLineRepo;
	
	@Autowired
	LineMonitor lineMonitor;
	
	public Set<Generation> getGenerationLevels(String transmissionLineName){
		TransmissionLine transmissionLine = transmissionLineRepo.findByName(transmissionLineName);
		return transmissionLine.getGenerations();
	}
	
	public Generation getGenerationByName(String generationName , String transmissionLineName){
		TransmissionLine transmissionLine = transmissionLineRepo.findByName(transmissionLineName);
		Optional<Generation> gen = transmissionLine.getGenerations().stream().filter(unit -> unit.getName().equals(generationName)).findFirst();
		return gen.orElse(null);
	}
	
	public TransmissionLine updateLevelGeneration(String generationName , String transmissionLineName, BigDecimal capacity){
		Generation generation = getGenerationByName(generationName , transmissionLineName);
		generation.setGenerationLevel(capacity);
		lineMonitor.checkLineCapacity();
		return this.getTransmissionLine(transmissionLineName);
	}


	@Override
	public TransmissionLine getTransmissionLine(String name) {
		return transmissionLineRepo.findByName(name);
	}

	@Override
	public List<TransmissionLine> getAllTransmissionLines() {
		// TODO Auto-generated method stub
		return transmissionLineRepo.findAll();
	}

	@Override
	public TransmissionLine updateCapacity(String line, int newCapacity) {
		this.getTransmissionLine(line).setCapacity(Integer.valueOf(newCapacity));
		lineMonitor.checkLineCapacity();
		return this.getTransmissionLine(line);
	}
	
	@Override
	public String checkAllLines() {
		return lineMonitor.checkLineStatus();
	}
}
