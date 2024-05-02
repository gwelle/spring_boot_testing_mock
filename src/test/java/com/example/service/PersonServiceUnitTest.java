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
	@DisplayName("Should returns a person according to your id")
	void givenPerson_shouldReturnsOnePersonById() {

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

	@Test
	@DisplayName("Should created a new person")
	void givenPerson_shouldReturnOnePersonCreated() {

		// GIVEN
		var pers = new Person((long) 4, "Wick", "John");
		when(personRepository.saveAndFlush(pers)).thenReturn(pers);

		// WHEN
		Person result = personService.saveOrFlushPerson(pers);

		// THEN
		verify(personRepository, times(1)).saveAndFlush(pers);
		assertEquals(pers, result);
	}

	@Test
	@DisplayName("Should delete a person")
	void gitvenPerson_shouldDeleteOnePerson() {

		// GIVEN
		int id = 1;

		// WHEN
		personService.deletePerson((long) 1);

		// THEN
		verify(personRepository, times(1)).deleteById((long) id);
	}

	@Test
	@DisplayName("Should update a person")
	void givenPerson_shouldReturnOnePersonUpdated() {

		// GIVEN
		var personne = new Person((long) 1, "Benjamin", "WELLE");
		when(personRepository.saveAndFlush(personne)).thenReturn(personne);

		// WHEN
		Person resultat = personService.saveOrFlushPerson(personne);

		// THEN
		verify(personRepository, times(1)).saveAndFlush(personne);
		assertEquals(personne, resultat);

	}
}