package com.example.demo.client;

import java.util.List;

import com.example.demo.model.Employee;

public interface EmployeeClient {
	
	public List<Employee> retrieveEmployees();
	Employee retrieveEmployee(long id);
	Employee createEmployee(Employee employee);
	void updateEmployee(Employee employee);
	void deleteEmployee(long id);
	public List<Employee> searchEmployeesByFirstNameAndLastName(String firstName, String lastName);

}
