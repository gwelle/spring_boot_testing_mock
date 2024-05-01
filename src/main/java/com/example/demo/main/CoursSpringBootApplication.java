package com.example.demo.main;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.demo.property.UserProperty;

@SpringBootApplication
@EnableConfigurationProperties(UserProperty.class)
@EnableAutoConfiguration(exclude = { ErrorMvcAutoConfiguration.class })
@ComponentScan(basePackages = { "com.example.demo" })
@EntityScan(basePackages = "com.example.demo.model")
@EnableJpaRepositories(basePackages = "com.example.demo.dao")
public class CoursSpringBootApplication implements ApplicationRunner {

	// @Autowired
	// private PersonService personService;

	public static void main(String[] args) {
		// System.setProperty("server.servlet.context-path", "/boot");
		SpringApplication.run(CoursSpringBootApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

		/*
		 * Person person = new Person("Guillaume", "WELLE"); Person otherPerson = new
		 * Person("Mercy", "GUENECOURD"); Address address = new Address();
		 * address.setCity("Paris"); address.setStreet("4 rue Louis le Grand");
		 * address.setZipCode("94190");
		 * 
		 * List<Address> addresses = Arrays.asList(address);
		 * person.setAddresses(addresses); otherPerson.setAddresses(addresses);
		 * 
		 * personService.saveOrFlushPerson(person);
		 * personService.saveOrFlushPerson(otherPerson);
		 */
	}
}