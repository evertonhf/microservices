package com.entities.structure.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.entities.structure.IEntityService;
import com.entities.structure.entities.Entity;

@RestController
public class EntityResource {

	@Autowired
	private IEntityService entityObjectService;

	@PostMapping
	public ResponseEntity<Object> createEntity(@RequestBody Entity entity) {
		try {
			entity = entityObjectService.save(entity);
		} catch (DuplicateKeyException e) {
			return new ResponseEntity<>("Chave duplicada:'name'", HttpStatus.BAD_REQUEST);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>("Entidade nula", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>("Erro inesperado, estamos solucionando!.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(entity, HttpStatus.CREATED);
	}

	@GetMapping("{entity}")
	public ResponseEntity<Object> readEntities(@PathVariable String entity) {
		try {
			return new ResponseEntity<>(entityObjectService.readByResource(entity), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Erro inesperado, estamos solucionando!.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping
	public ResponseEntity<Object> readEntity() {
		try {
			return new ResponseEntity<>(entityObjectService.readAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Erro inesperado, estamos solucionando!.", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping
	public ResponseEntity<Object> updateEntityStructure(@RequestBody Entity structure) {
		return new ResponseEntity<>("Recurso não implementado!.", HttpStatus.NOT_IMPLEMENTED);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Object> deleteEntityStructure(@PathVariable String id) {
		try {
			entityObjectService.delete(id);
			return new ResponseEntity<>(null, HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>("Entidade nula", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>("Erro inesperado, estamos solucionando!.", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@DeleteMapping
	public ResponseEntity<Object> deleteEntityStructure() {
		try {
			entityObjectService.deleteAll();
			return new ResponseEntity<>(null, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Erro inesperado, estamos solucionando!.", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
