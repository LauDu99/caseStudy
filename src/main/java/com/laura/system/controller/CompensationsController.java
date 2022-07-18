package com.laura.system.controller;

import java.text.ParseException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.laura.system.entitys.Compensation;
import com.laura.system.entitys.Employee;
import com.laura.system.service.CompensationsService;

@Controller
public class CompensationsController {

	@Autowired
	private CompensationsService service;

	@RequestMapping("/employee/addCompensationDetails/{id}")
	public String compensationDetailsForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("employee", service.getEmployee(id));
		Compensation compensation = new Compensation();
		model.addAttribute("Compensation", compensation);
		return "addCompensations";
	}

	@RequestMapping("/createCompensations/{id}")
	public String createCompensationDetails(@PathVariable("id") Long id,
			@ModelAttribute("Compensation") @Validated Compensation compensation) {
		Employee employee = service.getEmployee(id);
		compensation.setIdEmployee(employee);
		// if salary is null save, else
		if (service.validatedAmount(compensation)) {
			service.saveCompensation(compensation);
			return "redirect:/infoEmployee/{id}";
		}
		return "Employees";
	}
	/*
	 * List<Compensation> compensationList = service.getCompensationsEmployee(id);
	 * for (Compensation c : compensationList) { if
	 * (compensationList.contains("Salary")) { return "Employees"; } else if
	 * (service.validatedAmount(compensation)) {
	 * service.saveCompensation(compensation); return "redirect:/infoEmployee/{id}";
	 * } } return "Employees"; }
	 */

	@RequestMapping("/historyEmployee/compensations/{id}")
	public String allhistoryEmployee(@PathVariable("id") Long id, Model model) {
		model.addAttribute("Employee", service.getEmployee(id));
		model.addAttribute("compensationList", service.getCompensationsEmployee(id));
		return "historyEmployee";
	}

	@RequestMapping("/employee/compensationsByDate/{id}")
	public String findCompensationsEmployeeByDate(Model model, @PathVariable("id") Long id, String startDate,
			String endDate) throws ParseException {
		model.addAttribute("Employee", service.getEmployee(id));
		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);
		List<Compensation> compensationsList = service.getCompensationsByDate(id, startDate, endDate);
		model.addAttribute("compensationList", service.getCompensationsByDate(id, startDate, endDate));
		model.addAttribute("total", service.getTotal(compensationsList));

		if (compensationsList.isEmpty()) {
			model.addAttribute("msg", "No compensations to show");
			return "historyEmployee";
		}
		return "historyEmployeeByDate";
	}

	@RequestMapping("/employee/breakdownCompensations/{id}/{month}/{year}")
	public String breakdownCompensationsByMonth(Model model, @PathVariable("id") Long idEmployee,
			@PathVariable("month") int month, @PathVariable("year") int year) {
		model.addAttribute("employee", service.getEmployee(idEmployee));
		List<Compensation> compensationlist = service.getCompensationsByMonth(idEmployee, month, year);
		model.addAttribute("compensationlist", compensationlist);
		model.addAttribute("month", month);
		model.addAttribute("year", year);
		model.addAttribute("total", service.getTotal(compensationlist));

		return "compensationBreakdown";

	}

	@RequestMapping("/compensations/{id}")
	public String editCompensations() {
		return "editCompensations";
	}

}
