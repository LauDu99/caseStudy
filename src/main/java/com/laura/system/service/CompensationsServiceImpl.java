package com.laura.system.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laura.system.entitys.Compensation;
import com.laura.system.entitys.Employee;
import com.laura.system.repository.CompensationRepository;
import com.laura.system.repository.EmployeeRepository;

@Service
public class CompensationsServiceImpl implements CompensationsService {

	@Autowired
	private CompensationRepository repository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<Compensation> compensationsList(String keyWord) {
		if (keyWord != null) {
			return repository.findAll(keyWord);
		}
		return repository.findAll();
	}

	@Override
	public Compensation saveCompensation(Compensation compensation) {
		return repository.save(compensation);
	}

	@Override
	public Compensation getCompensation(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public void deleteCompensation(Long id) {
		repository.deleteById(id);
	}

	@Override
	public Employee getEmployee(Long id) {
		return employeeRepository.findById(id).get();
	}

	@Override
	public boolean validatedAmount(Compensation compensation) {
		String type = compensation.getType();
		BigDecimal amount = compensation.getAmount();

		if (type.equals("Salary") && amount.intValue() >= -0) {
			return true;
		} else {
			if (type.equals("Bonus") || type.equals("Comission") || type.equals("Allowance") && amount.intValue() > 0) {
				return true;
			} else if (type.equals("Adjustment") && amount.intValue() > 1) {
				return true;
			} else
				return false;
		}
	}

	@Override
	public List<Compensation> getCompensationsEmployee(Long idEmployee) {
		List<Compensation> employeeCompensations = repository.getCompensationsEmployeeById(idEmployee);
		return employeeCompensations;
	}

	@Override
	public List<Compensation> getCompensationsByDate(Long idEmployee, String firstDate, String secondDate)
			throws ParseException {

		DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate startDate = LocalDate.parse(firstDate, date);
		LocalDate endDate = LocalDate.parse(secondDate, date);

		List<Compensation> employeeCompensationsByDate = repository.getCompensationsEmployeeByDate(idEmployee,
				startDate, endDate);
		return employeeCompensationsByDate;
	}

	@Override
	public BigDecimal getTotal(List<Compensation> compensationList) {
		BigDecimal total = BigDecimal.ZERO;
		Iterator<Compensation> iterator = compensationList.iterator();
		if (compensationList != null) {
			if (iterator.hasNext()) {
				Compensation compensation = (Compensation) iterator.next();
				total = total.add(compensation.getAmount());
			}
		}
		return total;
	}

	@Override
	public List<Compensation> getCompensationsByMonth(Long idEmployee, int month, int year) {
		return repository.getCompensationsEmployeeByMonth(idEmployee, month, year);
	}
}
