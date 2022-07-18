package com.laura.system.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.laura.system.entitys.Employee;
import com.laura.system.service.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	//llama a la que va a implementar no a la que implementa
	private EmployeeService service;

	@GetMapping({"homePage.html","/homePage"})
	public String homePage() {
		return "homePage";
	}

	@RequestMapping("/addEmployee")
	public String addEmployee(Model modelo) {
		Employee employee = new Employee();
		modelo.addAttribute("Employee",employee);
		return "addEmployee";
	}
	//guardar empleado
	@RequestMapping("/Employees")
	public String createEmployee(@ModelAttribute("Employee") @Validated Employee employee, BindingResult bindingResult, RedirectAttributes ra) {
		if(bindingResult.hasErrors()) {
			return "addEmployee";
		}
		service.SaveEmployee(employee);
		ra.addAttribute("id", employee.getId()).addFlashAttribute("msg", "employee created");
		return "Employees";
	}
	
	@RequestMapping("/employee/edit/{id}")
	public String editEmployee(@PathVariable(name="id")Long id, Model model) {
		model.addAttribute("employee", service.getEmployee(id));
		return "editEmployee";
	}
	@RequestMapping("/employees/{id}")
	public String updateEmployee(@PathVariable(name="id") Long id, @ModelAttribute("employee") @Validated Employee employee, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "editEmployee";
		}
		Employee updatedEmployee= service.getEmployee(id);
		updatedEmployee.setFirstName(employee.getFirstName());
		updatedEmployee.setMiddleName(employee.getMiddleName());
		updatedEmployee.setLastName(employee.getLastName());
		updatedEmployee.setBirthDate(employee.getBirthDate());
		updatedEmployee.setPosition(employee.getPosition());
		service.SaveEmployee(updatedEmployee);
		return "redirect:/findEmployees";
	}
	
	@RequestMapping(value="/infoEmployee/{id}", method=RequestMethod.GET)
	public String updateEmployee(Model model, @PathVariable Long id) {
		model.addAttribute("employee", service.getEmployee(id));
		return "infoEmployee";
	}
	
	@RequestMapping("/findCompensations")
	public String findCompensations(Model model, @Param("keyWord") String keyWord) {
		List<Employee> employeeList = service.EmployeeList(keyWord);
		model.addAttribute("employeeList", employeeList);
		model.addAttribute("keyWord", keyWord);
		return "employeesCompensation";
	}
	
	@RequestMapping("/findEmployees")
	public String EmployeeList(Model model, @Param("keyWord") String keyWord) {
		List<Employee> employeeList = service.EmployeeList(keyWord);
		model.addAttribute("employeeList", employeeList);
		model.addAttribute("keyWord", keyWord);
		return "findEmployees";
	}
	@RequestMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable(name="id")Long id) {
		service.deleteEmployee(id);
		return "redirect:/findEmployees";
		
	}
		
}
