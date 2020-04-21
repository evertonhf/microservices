package com.entities.structure;

import java.util.List;

import org.springframework.dao.DuplicateKeyException;

import com.entities.structure.entities.Entity;

public interface IEntityService {

	Entity save(Entity entity) throws DuplicateKeyException, IllegalArgumentException;

	Entity readByResource(String entity);

	List<Entity> readAll();

	void delete(String id);

	void deleteAll();

}