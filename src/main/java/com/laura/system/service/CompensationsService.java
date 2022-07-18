package com.laura.system.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

import com.laura.system.entitys.Compensation;
import com.laura.system.entitys.Employee;

public interface CompensationsService {
	
	public List<Compensation> compensationsList(String keyWord);
	
	public Compensation saveCompensation(Compensation compensation);
	
	public Compensation getCompensation(Long id);
	
	public void deleteCompensation(Long id);
	
	public Employee getEmployee(Long id);
	
	public boolean validatedAmount(Compensation compensation);
	
	public List<Compensation> getCompensationsEmployee(Long idEmployee);
	
	public List<Compensation> getCompensationsByDate(Long idEmployee, String startDate, String endDate) throws ParseException;
	
	public List<Compensation> getCompensationsByMonth(Long idEmployee, int month, int year);
	
	public BigDecimal getTotal(List<Compensation> compensationList);

}
