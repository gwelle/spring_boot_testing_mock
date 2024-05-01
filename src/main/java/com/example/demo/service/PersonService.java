package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.dao.AddressRepository;
import com.example.demo.dao.PersonRepository;
import com.example.demo.model.Person;

@Service
public class PersonService {

	@Autowired
	PersonRepository personRepository;

	@Autowired
	AddressRepository addressRepository;

	public Person getPerson(final Long id) {

		var person = personRepository.findById(id).orElse(null);
		if (person == null) {
			// throw new PersonNotFoundException();
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found");
		}
		return person;
	}

	public Iterable<Person> getPersonsByLastName(String lastName) {
		return personRepository.findByLastname(lastName);
		// return personRepository.findByLastnameContaining(lastName);
	}

	public Iterable<Person> getPersonsByFistNameAndLastName(String firstName, String lastName) {
		return personRepository.findByFirstnameContainingAndLastnameContaining(firstName, lastName);
	}

	public Iterable<Person> getPersonsByFirstName(String firstName) {
		return personRepository.findByFirstname(firstName);
	}

	public Iterable<Person> getPersons() {
		return personRepository.findAll();
	}

	public void deletePerson(final Long id) {
		personRepository.deleteById(id);
	}

	public Optional<Person> getPersonById(final Long id) {
		return personRepository.findById(id);
	}

	public Person saveOrFlushPerson(Person person) {
		if (person.getAddresses() != null)
			addressRepository.saveAll(person.getAddresses());
		return personRepository.saveAndFlush(person);
	}
}
