package com.example.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.main.CoursSpringBootApplication;
import com.example.demo.model.Person;
import com.example.demo.service.PersonService;

@SpringBootTest(classes = CoursSpringBootApplication.class)
@AutoConfigureMockMvc // Annotation qui peut être appliquée à une classe de test
//pour activer et configurer l'auto-configuration de MockMvc.
class PersonRestControllerUnitTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PersonService personService;

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
	@DisplayName("Should returns a json of persons")
	void givenListOfPersons_shoudReturnsAjsonOfPersons() throws Exception {

		// GIVEN
		List<Person> fakedPersons = List.of(new Person("John", "Wick"), new Person("Benjamin", "Linus"),
				new Person("Jacques", "Pradel"));

		when(personService.getPersons()).thenReturn(fakedPersons);

		// WHEN
		this.mockMvc.perform(get("/personnes/").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$").isNotEmpty()).andExpect(jsonPath("$[0].firstName").value("John"))
				.andExpect(jsonPath("$[0].lastName").value("Wick"));

		// THEN
		verify(personService, times(1)).getPersons();
	}

	@Test
	@DisplayName("Should returns a json of with a person object")
	void givenPersonObject_shoudReturnsAjsonWithOnePerson() throws Exception {

		// GIVEN
		int id = 1;
		Optional<Person> person = Optional.of(new Person((long) 1, "John", "Wick"));
		when(personService.getPersonById((long) id)).thenReturn(person);

		// WHEN
		mockMvc.perform(get("/personnes/1")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$").isNotEmpty())
				.andExpect(jsonPath("$.firstName", is("John"))).andExpect(jsonPath("$.lastName", is("Wick")));

		// THEN
		verify(personService, times(1)).getPersonById((long) id);
	}

	@Test
	@DisplayName("Not Should returns a json of with a person object")
	void givenPersonObject_NotshoudReturnsAjsonWithOnePerson() throws Exception {

		// GIVEN
		int id = 1000;
		when(personService.getPersonById((long) id)).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND));

		// WHEN
		mockMvc.perform(get("/personnes/1000")).andExpect(status().isNotFound());

		// THEN
		verify(personService, times(1)).getPersonById((long) id);
	}
}