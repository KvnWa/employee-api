package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.client.EmployeeClient;
import com.example.demo.model.Employee;

@Service
public class EmployeeService {
	
	private EmployeeClient employeeClient;
	
	
	@Autowired 
	public EmployeeService(EmployeeClient employeeClient) {
		super();
		this.employeeClient = employeeClient;
	}


	public List<Employee> retrieveEmployees() {
		// TODO Auto-generated method stub
		return employeeClient.retrieveEmployees();
	}


	public Employee retrieveEmployee(long id) {
		// TODO Auto-generated method stub
		return employeeClient.retrieveEmployee(id);
	}


	public void createEmployee(Employee employee) {
		// TODO Auto-generated method stub
		employeeClient.createEmployee(employee);
	}


	public void updateEmployee(Employee employee) {
		employeeClient.updateEmployee(employee);
		
	}


	public void deleteEmployee(Long id) {
		employeeClient.deleteEmployee(id);
	}


	public List<Employee> searchEmployees(String firstName, String lastName) {
		// TODO Auto-generated method stub
		return employeeClient.searchEmployeesByFirstNameAndLastName(firstName, lastName);
	}

	

}
