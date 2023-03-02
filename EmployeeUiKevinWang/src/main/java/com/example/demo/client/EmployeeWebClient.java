package com.example.demo.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.model.Employee;

@Service
public class EmployeeWebClient implements EmployeeClient {
	
	private WebClient webClient;
	private final String BASE_URL = "/api/v1/employees";
	private final String BASE_URL_QUERY = "/api/v1/employees/{id}";
	
	
	@Autowired
	public EmployeeWebClient(WebClient webClient) {
		super();
		this.webClient = webClient;
	}

	@Override
	public List<Employee> retrieveEmployees() {
		return webClient.get()
				.uri(builder -> builder.path(BASE_URL).build())
				.retrieve()
				.bodyToFlux(Employee.class)
				.collectList()
				.block();
	}

	@Override
	public Employee retrieveEmployee(long id) {
		
		return webClient.get()
				.uri(builder -> builder.path(BASE_URL_QUERY).build(id))
				.retrieve()
				.bodyToMono(Employee.class)
				.block();
	}

	@Override
	public Employee createEmployee(Employee employee) {
	
		return webClient.post()
				.uri(builder -> builder.path(BASE_URL).build())
				.bodyValue(employee)
				.retrieve()
				.bodyToMono(Employee.class)
				.block();
	}

	@Override
	public void updateEmployee(Employee employee) {
			webClient.put()
			.uri(builder -> builder.path(BASE_URL + "/" + employee.getId()).build(employee.getId()))
			.bodyValue(employee)
			.retrieve()
			.bodyToMono(Employee.class)
			.block();

	}

	@Override
	public void deleteEmployee(long id) {
		webClient.delete()
		.uri(builder -> builder.path(BASE_URL_QUERY).build(id))
		.retrieve()
		.toBodilessEntity()
		.block();
		
	}

	@Override
	public List<Employee> searchEmployeesByFirstNameAndLastName(String firstName, String lastName) {
		// TODO Auto-generated method stub
		return webClient.get()
				.uri(builder-> builder.path(BASE_URL).queryParam("firstName", firstName)
						.queryParam("lastName", lastName)
						.build())
				.retrieve().bodyToFlux(Employee.class).collectList().block();
	}

}
