package com.everton.entities.service.utils;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.everton.entities.service.entities.Entity;
import com.everton.entities.service.entities.Property;

@Component
public class EndPointEntitiesService {

	private static final String LOCALHOST = "http://entities-template-service/";

	@Autowired
	private DiscoveryClient discoveryClient;

	@Autowired
	private RestTemplate restTemplate;

	/**
	 * Get Resource from Service
	 * 
	 * @param resource: entity name
	 * @return Template
	 */
	public Entity getEntityFromService(String resource) throws ResourceNotFound {
		Entity entity = restTemplate.getForObject(LOCALHOST + resource, Entity.class);
		if (entity == null) {
			throw new ResourceNotFound("Recurso inexistente!");
		}
		return entity;
	}

	/**
	 * Get Resource from Service and validate
	 * 
	 * @param resource:       entity name
	 * @param entitiesObjects
	 * @return
	 * @throws ResourceNotFound
	 * @throws ValidateFieldsException
	 */
	public void getEntityFromServiceValidate(String resource, Map<String, Object> entitiesObjects)
			throws ResourceNotFound, ValidateFieldsException, EntityIncompatibleWithObjectException {
		// get entity template
		Entity entity = getEntityFromService(resource);
		// validate value and type
		this.validateTypeFields(entitiesObjects, entity);
	}

	/**
	 * Validate Resource and values
	 * 
	 * @param entitiesObjects
	 * @param template
	 * @throws ValidateFieldsException
	 */
	public void validateTypeFields(Map<String, Object> entitiesObjects, Entity entity)
			throws ValidateFieldsException, EntityIncompatibleWithObjectException {
		for (Property property : entity.getProperties()) {
			// validate if field is required
			Object value = entitiesObjects.get(property.getName());
			if (value == null) {
				throw new EntityIncompatibleWithObjectException("Entidade incompatível com objeto!");
			}
			if (property.isRequired() && ((value instanceof String && ((String) value).isEmpty()))) {// exception fields
																										// required
				throw new ValidateFieldsException("Campos obrigatórios não preenchidos");
			}
			// validate type of data if value is not null
			if (value != null) {
				if (!value.getClass().getName().equalsIgnoreCase(property.getType())) {
					throw new ValidateFieldsException("Tipo de dados incoerente!");
				}
			}
		}
	};

}
