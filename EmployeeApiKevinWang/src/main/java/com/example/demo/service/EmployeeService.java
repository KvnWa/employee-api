package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeService {

	private final EmployeeRepository employeeRepository;

	@Autowired
	public EmployeeService(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}
	
	public Employee createEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}
	
	public List<Employee> getAllEmployees(){
		return employeeRepository.findAll();
	}
	
	public Employee getEmployeeById(Long id) {
		return employeeRepository.findById(id)
				.orElseThrow();
	}
	
	public Employee updateEmployee(Long id, Employee employee) {
		Optional<Employee> existingEmployee = employeeRepository.findById(id);
		if(existingEmployee.isPresent()) {
			Employee updateEmployee = existingEmployee.get();
			updateEmployee.setFirstName(employee.getFirstName());
			updateEmployee.setLastName(employee.getLastName());
			updateEmployee.setSalary(employee.getSalary());
			return employeeRepository.save(employee);
		}else
			return null;
	}
	
	public void deleteEmployee(Long id) {
		Employee existingEmployee = getEmployeeById(id);
		employeeRepository.delete(existingEmployee);
	}
	
	
}
