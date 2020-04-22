package com.everton.entities.service.utils;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.everton.entities.service.entities.Entity;
import com.everton.entities.service.entities.Property;

@Component
public class EndPointEntitiesService {

	private static final String entitiesTemplateServiceName = "entities-template-service";

	@Autowired
	private LoadBalancerClient loadBalancerClient;

	private RestTemplate restTemplate = new RestTemplate();

	/**
	 * Get Resource from Service
	 * 
	 * @param resource: entity name
	 * @return Template
	 */
	public Entity getEntityFromService(String resource) throws ResourceNotFound {

		ServiceInstance servInstance = loadBalancerClient.choose(entitiesTemplateServiceName);
		String baseUrl = servInstance.getUri().toString();
		baseUrl = baseUrl + "/" + resource;

		Entity entity = restTemplate.getForObject(baseUrl, Entity.class);

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
