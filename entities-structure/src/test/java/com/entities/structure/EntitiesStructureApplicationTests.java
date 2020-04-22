package com.entities.structure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.entities.structure.entities.Entity;
import com.entities.structure.entities.Property;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class EntitiesStructureApplicationTests {

	@Autowired
	private IEntityService entityObjectService;

	private static String resource = "Peoples";
	private static String resource02 = "Products";

	private static Entity entity, entity02;

	@BeforeAll
	static void setUp() throws Exception {
		Set<Property> properties = new HashSet<>();
		properties.add(new Property("name", "java.lang.String", true));
		properties.add(new Property("email", "java.lang.String", false));
		properties.add(new Property("idade", "java.lang.Integer", false));

		entity = new Entity(resource, properties);

		Set<Property> properties02 = new HashSet<>();
		properties.add(new Property("name", "java.lang.String", true));
		properties.add(new Property("email", "java.lang.String", false));
		properties.add(new Property("idade", "java.lang.Integer", false));

		entity02 = new Entity(resource02, properties02);
	}

	@Test
	@Order(1)
	void testCreateEntityStructureSuccess() throws Exception {
		Entity entityTmp = entityObjectService.save(entity);
		Entity entityTmp02 = entityObjectService.save(entity02);

		assertNotNull(entityTmp);
		assertNotNull(entityTmp.getId());
		assertEquals(resource, entityTmp.getResource());

		assertNotNull(entityTmp02);
		assertNotNull(entityTmp02.getId());
		assertEquals(resource02, entityTmp02.getResource());
	}

	@Test
	@Order(2)
	void testReadEntityStructureSuccess() throws Exception {
		Entity entityTmp = entityObjectService.readByResource(resource);
		assertNotNull(entity);
		assertEquals(entityTmp.getId(), entity.getId());
		assertEquals(resource, entityTmp.getResource());
	}

	@Test
	@Order(3)
	void testReadAllEntityStructureSuccess() throws Exception {
		List<Entity> entities = entityObjectService.readAll();
		assertNotNull(entities);
		assertTrue(entities.size() == 2);
	}

	@Test
	@Order(4)
	void testDeleteEntityStructureSuccess() throws Exception {
		entityObjectService.delete(entity02.getId());
		List<Entity> entities = entityObjectService.readAll();
		assertTrue(entities.size() == 1);
	}

}
