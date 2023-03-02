package com.example.demo.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.exception.EmployeeNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
	
	private final EmployeeService employeeService;

	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}
	
	
	@Operation(summary="Create a new employee resource")
	@ApiResponses(value= {
			@ApiResponse(responseCode="201", description="created successfully",
					headers= {@Header(name="location", description="URI to access the created Resource")},
					content= {@Content(mediaType=MediaType.APPLICATION_JSON_VALUE)})
	})
	@PostMapping
	public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee){
		Employee createEmployee = employeeService.createEmployee(employee);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id").buildAndExpand(employee.getId()).toUri();
		return ResponseEntity.created(location).body(createEmployee);
	}
	
	
	@Operation(summary="return all employees")
	@ApiResponses(value= {
			@ApiResponse(responseCode="200", description="successfully",
					content= {@Content(mediaType=MediaType.APPLICATION_JSON_VALUE)})
	})
	@GetMapping
	public ResponseEntity<List<Employee>> getAllEmployees(){
		return ResponseEntity.ok(employeeService.getAllEmployees());
	}
	
	@Operation(summary="Get employee by id")
	@ApiResponses(value= {
			@ApiResponse(responseCode="200", description="employee fetched by id successfully",
					content= {@Content(mediaType=MediaType.APPLICATION_JSON_VALUE)})
	})
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable long id){
		return ResponseEntity.ok(employeeService.getEmployeeById(id));
	}
	
	@Operation(summary = "Update an employee by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee updated successfully",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "404", description = "Employee not found",
            content = @Content)
    })
	@PutMapping("/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable long id, @Valid @RequestBody Employee employee) throws EmployeeNotFoundException{
		Employee updatedEmployee = employeeService.updateEmployee(id, employee);
		if(updatedEmployee == null) {
			throw new EmployeeNotFoundException("Employee not found with id: "+id);
		}
		return ResponseEntity.ok(updatedEmployee);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable long id){
		employeeService.deleteEmployee(id);
		return ResponseEntity.noContent().build();
	}
	

	
	
	

}
