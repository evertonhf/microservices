package com.everton.entities.service.reposiroty;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.everton.entities.service.entities.EntityObject;

public interface EntityObjectRepository extends MongoRepository<EntityObject, String> {
	public List<EntityObject> findByResource(String resource);
	public EntityObject findByIdAndResource(String id, String resource);
	public void deleteByIdAndResource(String id, String resource);
}
