package com.everton.entities.service;

import java.util.List;
import java.util.Map;

import com.everton.entities.service.entities.EntityObject;
import com.everton.entities.service.utils.EntityIncompatibleWithObjectException;
import com.everton.entities.service.utils.ResourceNotFound;
import com.everton.entities.service.utils.ValidateFieldsException;

public interface IEntityObjectService {

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
	EntityObject save(String resource, Map<String, Object> attributesEntity)
			throws ResourceNotFound, ValidateFieldsException, EntityIncompatibleWithObjectException;

	/**
	 * Find object saved by Id from resource.
	 * 
	 * @param id       - String that identifies the object.
	 * @param resource - resource name to find.
	 * @return EntityObject with values saved.
	 * @throws ResourceNotFound - - exception generated when a resource is not
	 *                          found.
	 */
	EntityObject find(String id, String resource) throws ResourceNotFound;

	/**
	 * Find list object saved from resource.
	 * 
	 * @param resource - resource name to find.
	 * @return List<EntityObject> with values saved.
	 * @throws ResourceNotFound - - exception generated when a resource is not
	 *                          found.
	 */
	List<EntityObject> findAll(String resource) throws ResourceNotFound;

	void delete(String id, String resource) throws ResourceNotFound;

}