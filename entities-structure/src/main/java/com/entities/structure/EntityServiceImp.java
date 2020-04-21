package com.entities.structure;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.entities.structure.entities.Entity;
import com.entities.structure.repository.EntityRepository;

@Service
public class EntityServiceImp implements IEntityService {

	@Autowired
	EntityRepository entityRepository;

	@Override
	public Entity save(Entity entity) throws DuplicateKeyException, IllegalArgumentException {
		return entityRepository.save(entity);
	}

	@Override
	public Entity readByResource(String entity) {
		return entityRepository.findByResource(entity);
	}

	@Override
	public List<Entity> readAll() {
		return entityRepository.findAll();
	}

	@Override
	public void delete(String id) {
		entityRepository.deleteById(id);
	}

	@Override
	public void deleteAll() {
		entityRepository.deleteAll();
	}

}
