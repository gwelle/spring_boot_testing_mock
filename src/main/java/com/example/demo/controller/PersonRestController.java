package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;

@CrossOrigin
@RestController
@RequestMapping("/personnes")
public class PersonRestController {

	@Autowired
	private PersonService personService;

	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public Iterable<Person> getPersons() {
		return personService.getPersons();
	}

	@PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public Person addPerson(@RequestBody Person person) {
		return personService.saveOrFlushPerson(person);
	}

	@GetMapping(value = "/lastName", produces = MediaType.APPLICATION_JSON_VALUE)
	public Iterable<Person> getPersonsByLastName(@RequestParam String lastName) {
		return personService.getPersonsByLastName(lastName);
	}

	@GetMapping(value = "/persons", produces = MediaType.APPLICATION_JSON_VALUE)
	public Iterable<Person> getPersonsByFistNameAndLastName(@RequestParam String firstName,
			@RequestParam String lastName) {
		return personService.getPersonsByFistNameAndLastName(firstName, lastName);
	}

	@GetMapping(value = "/firstName", produces = MediaType.APPLICATION_JSON_VALUE)
	public Iterable<Person> getPersonsByFirstName(@RequestParam String firstName) {
		return personService.getPersonsByFirstName(firstName);
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<Person> getPerson(@PathVariable Long id) {
		return personService.getPersonById(id);
	}

	@PatchMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Person updatePerson(@RequestBody Person person, @PathVariable Long id) {
		Optional<Person> currentPerson = getPerson(id);
		person.setId(id);
		person.setFirstName(person.getFirstName() == null ? currentPerson.get().getFirstName() : person.getFirstName());
		person.setLastName(person.getLastName() == null ? currentPerson.get().getLastName() : person.getLastName());
		return personService.saveOrFlushPerson(person);
	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void removePerson(@PathVariable Long id) {
		personService.deletePerson(id);
	}
}
