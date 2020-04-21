package com.everton.entities.service.entities;

import java.util.HashSet;
import java.util.Set;

public class Entity {

	private String id;
	private String resource;
	private Set<Property> properties = new HashSet<Property>();

	public void addProperty(Property propriety) {
		this.properties.add(propriety);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Set<Property> getProperties() {
		return properties;
	}

	public void setProperties(Set<Property> proprieties) {
		this.properties = proprieties;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String name) {
		this.resource = name;
	}

}
