package com.laura.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laura.system.entitys.Employee;
import com.laura.system.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepository repository;
	
	
	@Override
	public List<Employee> EmployeeList(String keyWord) {
		if(keyWord != null) {
			return repository.findAll(keyWord);
		}
		return repository.findAll();
	}
	@Override
	public Employee SaveEmployee(Employee employee) {
		return repository.save(employee);
	}
	@Override
	public Employee getEmployee(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public void deleteEmployee(Long id) {
		repository.deleteById(id);
	}

}
