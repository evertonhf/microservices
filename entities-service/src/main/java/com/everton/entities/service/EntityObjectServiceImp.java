package com.everton.entities.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.everton.entities.service.entities.EntityObject;
import com.everton.entities.service.reposiroty.EntityObjectRepository;
import com.everton.entities.service.utils.EndPointEntitiesService;
import com.everton.entities.service.utils.EntityIncompatibleWithObjectException;
import com.everton.entities.service.utils.ResourceNotFound;
import com.everton.entities.service.utils.ValidateFieldsException;

@Service
public class EntityObjectServiceImp implements IEntityObjectService {

	@Autowired
	EntityObjectRepository entityObjectRepository;

	@Autowired
	private EndPointEntitiesService endPointEntitiesService;

	/**
	 * Save a new entity/Class.
	 * 
	 * @param resource         - entity name.
	 * @param attributesEntity - String object in JSON format.
	 * @return object entity saved.
	 * @throws ResourceNotFound                      - exception generated when
	 *                                               resource solicited not exists.
	 * @throws ValidateFieldsException               - exception generated when
	 *                                               fields types and values are not
	 *                                               in the resource default.
	 * @throws EntityIncompatibleWithObjectException
	 */
	@Override
	public EntityObject save(String resource, Map<String, Object> attributesEntity)
			throws ResourceNotFound, ValidateFieldsException, EntityIncompatibleWithObjectException {
		EntityObject entityObject;
		// extract id from attributesEntity that will be Save or UPDATE
		String id = (String) attributesEntity.remove("id");
		// validate entity, values and fields type
		endPointEntitiesService.getEntityFromServiceValidate(resource, attributesEntity);
		// set the values for object will be saved
		entityObject = new EntityObject(id, resource, attributesEntity);
		return entityObjectRepository.save(entityObject);
	}

	/**
	 * Find object saved by Id from resource.
	 * 
	 * @param id       - String that identifies the object.
	 * @param resource - resource name to find.
	 * @return EntityObject with values saved.
	 * @throws ResourceNotFound - - exception generated when a resource is not
	 *                          found.
	 */
	@Override
	public EntityObject find(String id, String resource) throws ResourceNotFound {
		// get resource object from service or ResourceNotFound
		endPointEntitiesService.getEntityFromService(resource);
		return entityObjectRepository.findByIdAndResource(id, resource);
	}

	/**
	 * Find list object saved from resource.
	 * 
	 * @param resource - resource name to find.
	 * @return List<EntityObject> with values saved.
	 * @throws ResourceNotFound - - exception generated when a resource is not
	 *                          found.
	 */
	@Override
	public List<EntityObject> findAll(String resource) throws ResourceNotFound {
		// get resource object from service or ResourceNotFound
		endPointEntitiesService.getEntityFromService(resource);
		return entityObjectRepository.findByResource(resource);
	}

	@Override
	public void delete(String id, String resource) throws ResourceNotFound {
		// get resource object from service or ResourceNotFound
		endPointEntitiesService.getEntityFromService(resource);
		entityObjectRepository.deleteByIdAndResource(id, resource);
	}

}
