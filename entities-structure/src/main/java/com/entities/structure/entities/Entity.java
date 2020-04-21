package com.entities.structure.entities;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Entity {

	@Id
	private String id;

	@Indexed(name = "index_name", unique = true, direction = IndexDirection.ASCENDING)
	private String resource;

	private Set<Property> properties = new HashSet<Property>();

	public Entity() {
		super();
	}

	public Entity(String resource, Set<Property> properties) {
		super();
		this.resource = resource;
		this.properties = properties;
	}

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
