package com.laura.system.service;

import java.util.List;

import com.laura.system.entitys.Employee;

public interface EmployeeService {

	public List<Employee> EmployeeList(String keyWord);
	
	public Employee SaveEmployee(Employee employee);
	
	public Employee getEmployee(Long id);
	
	public void deleteEmployee(Long id);
	
	
	
}
