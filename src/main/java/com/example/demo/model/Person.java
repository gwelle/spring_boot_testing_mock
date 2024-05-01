package com.example.demo.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "person")

public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NonNull
	// @Size(min=2)
	// @NotEmpty(message="The field firstName is required")
	private String firstname;
	@NonNull
	private String lastname;

	@NonNull
	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.EAGER)
	private List<Address> addresses;

	public Person() {
	}

	public Person(Long id, @NonNull String firstname, @NonNull String lastname) {
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public Person(String firstName, String lastName) {
		this.firstname = firstName;
		this.lastname = lastName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstname;
	}

	public void setFirstName(String firstName) {
		this.firstname = firstName;
	}

	public String getLastName() {
		return lastname;
	}

	public void setLastName(String lastName) {
		this.lastname = lastName;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public boolean add(Address address) {
		return addresses.add(address);
	}

	public boolean remove(Address address) {
		return addresses.remove(address);
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", firstName=" + firstname + ", lastName=" + lastname + "]";
	}
}