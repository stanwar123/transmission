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
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class TransmissionLine implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	public TransmissionLine() {
	}

	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private Integer capacity;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "transmissionLine")
	private Set<Generation> generations;
	
	public Set<Generation> getGenerations() {
		return generations;
	}

	public void setGenerations(Set<Generation> generations) {
		this.generations = generations;
	}


	public TransmissionLine(Long id, String name, Set<Generation> generations) {
		super();
		this.name = name;
		this.generations = generations;
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

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	@Override
	public String toString() {
		return "TransmissionLine [id=" + id + ", name=" + name + ", capacity=" + capacity + ", generations="
				+ generations + "]";
	}

}
