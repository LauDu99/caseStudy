package com.laura.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.laura.system.entitys.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	@Query("SELECT e FROM Employee e WHERE" + " CONCAT(e.firstName, e.lastName, e.birthDate, e.position)"
			+ " LIKE %?1%")
	public List<Employee> findAll(String keyWord);
}
