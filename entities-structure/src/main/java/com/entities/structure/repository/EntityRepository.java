package com.entities.structure.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.entities.structure.entities.Entity;

public interface EntityRepository extends MongoRepository<Entity, String> {

	public Entity findByResource(String resource);
	
}
