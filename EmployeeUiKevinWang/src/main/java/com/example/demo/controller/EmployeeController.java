package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;

@Controller
public class EmployeeController {

	private EmployeeService employeeService;
	private final ApplicationContext context;

	
	@Autowired
	public EmployeeController(EmployeeService employeeService, ApplicationContext context) {
		super();
		this.employeeService = employeeService;
		this.context = context;
	}

	@GetMapping("/")
	public String index(Model model) {
		List<Employee> employees = employeeService.retrieveEmployees();
		model.addAttribute("employees", employees);
		return "index";
	}
	
	@GetMapping("/view/{id}")
	public String getContact(@PathVariable Long id, Model model) {
		Employee employee = employeeService.retrieveEmployee(id);
		model.addAttribute("employee", employee);
		return "employee-details";
	}
	
	@GetMapping("/createEmployee")
	public String addEmployeePage(Model model) {
		Employee employee = context.getBean(Employee.class);
		model.addAttribute("employee", employee);
		return "create-employee";
		
	}
	
	@PostMapping("/createEmployee")
	public String addNewEmployee(Employee employee) {
		employeeService.createEmployee(employee);
		return "redirect:/";
		
	}
	
	@GetMapping("/edit/{id}")
	public String editContact(@PathVariable Long id, Model model) {
		Employee employee = employeeService.retrieveEmployee(id);
		model.addAttribute("employee", employee);
		return "edit-employee";
	}
	
	@PostMapping("/updateEmployee")
	public String updateEmployee(Employee employee) {
		employeeService.updateEmployee(employee);
		return "redirect:/";
	}
	
	
	@GetMapping("/delete/{id}")
	public String deleteEmployee(@PathVariable Long id) {
		employeeService.deleteEmployee(id);
		return "redirect:/";
	}
	
	@GetMapping("/search")
	public String searchEmployees(Model model, @RequestParam("firstName") String firstName, @RequestParam("lastName")String lastName) {
		List<Employee> employees = employeeService.searchEmployees(firstName, lastName);
		model.addAttribute("employees", employees);
		return "search-result";
	}
	
	
	
	
	
	
}
