package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Person;

@Transactional
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

	Iterable<Person> findByLastname(String lastname);

	Iterable<Person> findByLastnameContaining(String lastname);

	Iterable<Person> findByFirstnameContainingAndLastnameContaining(String firstname, String lastname);

	// @Query("SELECT p FROM Person p WHERE p.firstname = ?1")
	// Iterable<Person> findByFirstname(String firstname);

	@Query("SELECT p FROM Person p WHERE p.firstname = :firstname")
	Iterable<Person> findByFirstname(@Param("firstname") String firstname);

}