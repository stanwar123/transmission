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

package com.hack.TransmissionLineService.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Generation implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	public Generation() {
	}

	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private BigDecimal generationLevel;

	public BigDecimal getGenerationLevel() {
		return generationLevel;
	}

	public void setGenerationLevel(BigDecimal generationLevel) {
		this.generationLevel = generationLevel;
	}

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="transmissionLine_id", nullable=false)
	private TransmissionLine transmissionLine;


	public Generation(String name, BigDecimal generationLevel, TransmissionLine transmissionLine) {
		super();
		this.name = name;
		this.generationLevel=generationLevel;
		this.transmissionLine = transmissionLine;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TransmissionLine getTransmissionLine() {
		return transmissionLine;
	}

	public void setTransmissionLine(TransmissionLine transmissionLine) {
		this.transmissionLine = transmissionLine;
	}

	@Override
	public String toString() {
		return "Generation [id=" + id + ", name=" + name + ", transmissionLine=" + transmissionLine.getName() + "]";
	}

}
