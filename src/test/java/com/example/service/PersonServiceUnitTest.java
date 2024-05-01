package com.example.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.dao.PersonRepository;
import com.example.demo.main.CoursSpringBootApplication;
import com.example.demo.model.Person;
import com.example.demo.service.PersonService;

@SpringBootTest(classes = {
		CoursSpringBootApplication.class }, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PersonServiceUnitTest {

	@MockBean
	private PersonRepository personRepository;

	@Autowired
	PersonService personService;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@DisplayName("Should returns List of Persons")
	void givenPersonsList_shouldReturnsPersonsList() {

		// GIVEN
		List<Person> fakedPersons = List.of(new Person("Guillaume", "WELLE"), new Person("Benjamin", "WELLE"),
				new Person("Mercy", "GUENECOURD"));

		when(personRepository.findAll()).thenReturn(fakedPersons);

		// WHEN
		var results = personService.getPersons();

		// THEN
		verify(personRepository, times(1)).findAll();
		Assertions.assertIterableEquals(fakedPersons, results);
	}

	@Test
	@DisplayName("Should returns an person according to your id")
	void givenPerson_shouldReturnOnePersonById() {

		// GIVEN
		int id = 3;
		Optional<Person> person = Optional.of(new Person((long) 3, "Wick", "John"));
		when(personRepository.findById((long) id)).thenReturn(person);

		// WHEN
		var res = personService.getPersonById((long) id);

		// THEN
		verify(personRepository, times(1)).findById((long) id);
		assertEquals(person, res);
	}
	/*
	 * 
	 * @Test void testSave() { fail("Not yet implemented"); }
	 * 
	 * @Test void testDeleteById() { fail("Not yet implemented"); }
	 * 
	 * @Test void testUpdate() { fail("Not yet implemented"); }
	 */

}
