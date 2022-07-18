package com.laura.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.laura.system.repository.EmployeeRepository;

@SpringBootApplication
public class EmploymentSystemApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(EmploymentSystemApplication.class, args);
	}
	
	@Autowired
	private EmployeeRepository repository;

	@Override
	public void run(String... args) throws Exception {
		//Employee employee = new Employee("Laura", "Rocio", "Duran", "99-05-23", "estudiante");
		//repository.save(employee);
	}
	
	

}
