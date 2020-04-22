package com.everton.entities.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.everton.entities.service.entities.EntityObject;
import com.everton.entities.service.utils.EntityIncompatibleWithObjectException;
import com.everton.entities.service.utils.ResourceNotFound;
import com.everton.entities.service.utils.ValidateFieldsException;

//@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@TestMethodOrder(OrderAnnotation.class)
class EntitiesServiceApplicationTests {

	@Autowired
	private IEntityObjectService entityObjectService;

	private static EntityObject entityObject;

	private static Map<String, Object> attributesValues = new HashMap<>();

	private String resource = "Peoples";

	@BeforeAll
	static void setUp() throws Exception {
		attributesValues.put("name", "Mario eduardo");
		attributesValues.put("email", "md@gmail.com");
		attributesValues.put("idade", 34);
	}

	@Test
	@Order(1)
	void testCreateEntitySuccess() throws Exception {
		entityObject = entityObjectService.save(resource, attributesValues);
		assertNotNull(entityObject);
		assertNotNull(entityObject.getId());
		assertEquals(resource, entityObject.getResource());
		assertEquals((String) attributesValues.get("name"), entityObject.getProperties().get("name"));
		assertEquals((String) attributesValues.get("email"), entityObject.getProperties().get("email"));
	}

	@Test
	@Order(2)
	void testCreateEntityFailResourceNotFound() throws Exception {
		Exception exception = assertThrows(ResourceNotFound.class, () -> {
			entityObjectService.save("other", attributesValues);
		});
	}

	@Test
	@Order(3)
	void testCreateEntityFailEntityIncompatibleWithObjectException() throws Exception {
		Map<String, Object> attributesValues = new HashMap<>();
		attributesValues.put("name", "Mario eduardo");
		attributesValues.put("email", "md@gmail.com");
		/*
		 * ausência de um atributo da entidade.
		 */
		Exception exception = assertThrows(EntityIncompatibleWithObjectException.class, () -> {
			entityObjectService.save(resource, attributesValues);
		});
	}

	@Test
	@Order(4)
	void testCreateEntityFailEntityValidateFieldsException() throws Exception {
		Map<String, Object> attributesValues = new HashMap<>();
		attributesValues.put("name", 1);
		attributesValues.put("email", 1);
		attributesValues.put("idade", 34);
		/*
		 * valores incompatíves com os atributos definidos na classe
		 */
		Exception exception = assertThrows(ValidateFieldsException.class, () -> {
			entityObjectService.save(resource, attributesValues);
		});
	}

	@Test
	@Order(5)
	void testReadEntityStringString() throws ResourceNotFound {
		EntityObject entityObject = entityObjectService.find(this.entityObject.getId(), resource);
		assertNotNull(entityObject);
		assertNotNull(entityObject.getId());
		assertEquals(resource, entityObject.getResource());
		assertEquals((String) attributesValues.get("name"), entityObject.getProperties().get("name"));
		assertEquals((String) attributesValues.get("email"), entityObject.getProperties().get("email"));
	}

	@Test
	@Order(6)
	void testReadEntityString() throws ResourceNotFound {
		List<EntityObject> entitiesObjects = entityObjectService.findAll(resource);
		assertNotNull(entitiesObjects);
		assertTrue(entitiesObjects.size() > 0);
	}

	@Test
	@Order(7)
	void testUpdateEntity() throws Exception {
		attributesValues.put("name", "Joana");
		attributesValues.put("email", "joana@bol.com");
		attributesValues.put("idade", 99);
		entityObject.setProperties(attributesValues);
		this.testCreateEntitySuccess();
	}

}
