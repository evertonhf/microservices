package com.everton.entities.service.resources;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.everton.entities.service.IEntityObjectService;
import com.everton.entities.service.utils.EntityIncompatibleWithObjectException;
import com.everton.entities.service.utils.ResourceNotFound;
import com.everton.entities.service.utils.ValidateFieldsException;

@RestController
public class EntityObjectResource {

	@Autowired
	private IEntityObjectService entityObjectService;

	/**
	 * 
	 * @param resource
	 * @param attributesEntity
	 * @return ResponseEntity<Object>
	 */
	@PostMapping("{resource}")
	public ResponseEntity<Object> createEntityStructure(@PathVariable String resource,
			@RequestBody Map<String, Object> attributesEntity) {
		try {
			return new ResponseEntity<>(entityObjectService.save(resource, attributesEntity), HttpStatus.CREATED);
		} catch (EntityIncompatibleWithObjectException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>("Entidade nula", HttpStatus.BAD_REQUEST);
		} catch (ResourceNotFound e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (ValidateFieldsException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>("Erro inesperado, estamos solucionando!.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Get Object by id
	 * 
	 * @param resource
	 * @param id
	 * @return
	 */
	@GetMapping("{resource}/{id}")
	public ResponseEntity<Object> readEntityStructure(@PathVariable String resource, @PathVariable String id) {
		try {
			return new ResponseEntity<>(entityObjectService.find(id, resource), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>("Entidade nula", HttpStatus.BAD_REQUEST);
		} catch (ResourceNotFound e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>("Erro inesperado, estamos solucionando!.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("{resource}")
	public ResponseEntity<Object> readEntityStructure(@PathVariable String resource) {
		try {
			return new ResponseEntity<>(entityObjectService.findAll(resource), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>("Entidade nula", HttpStatus.BAD_REQUEST);
		} catch (ResourceNotFound e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>("Erro inesperado, estamos solucionando!.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping()
	public ResponseEntity<Object> updateEntityStructure(@PathVariable String resource,
			@RequestBody Map<String, Object> entitiesObjects) {
		return new ResponseEntity<>("Utilize método POST para atualização!", HttpStatus.NOT_IMPLEMENTED);
	}

	@DeleteMapping("{resource}/{id}")
	public ResponseEntity<Object> deleteEntityStructure(@PathVariable String resource, @PathVariable String id) {
		try {
			entityObjectService.delete(id, resource);
			return new ResponseEntity<>(null, HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>("Entidade nula", HttpStatus.BAD_REQUEST);
		} catch (ResourceNotFound e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>("Erro inesperado, estamos solucionando!.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
