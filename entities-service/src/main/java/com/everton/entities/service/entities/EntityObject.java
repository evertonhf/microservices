package com.everton.entities.service.entities;

import java.util.HashMap;
import java.util.Map;

public class EntityObject {

	private String id;
	private String resource;
	private Map<String, Object> properties = new HashMap<String, Object>();

	
	
	public EntityObject() {
		super();
	}

	/**
	 * Relate entity to object values.
	 * 
	 * @param nameClassTemplate - String that define which entity belong
	 * @param properties        - Map<String, Object> object pairs (property, value)
	 */
	public EntityObject(String resource, Map<String, Object> properties) {
		super();
		this.resource = resource;
		this.properties = properties;
	}

	public EntityObject(String id, String resource, Map<String, Object> properties) {
		super();
		this.id = id;
		this.resource = resource;
		this.properties = properties;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public Map<String, Object> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}

}
