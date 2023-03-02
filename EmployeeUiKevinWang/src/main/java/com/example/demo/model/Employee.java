package com.example.demo.model;

import java.math.BigDecimal;
import java.util.Objects;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Component
@Scope("prototype")
public class Employee {
	
	private long id;
	@NotBlank(message="First name is required")
	private String firstName;
	@NotBlank(message="Last name is required")
	private String lastName;
	@NotNull(message = "salary is required")
	private BigDecimal salary;
	@NotBlank(message="state is required")
	private String state;
	@NotBlank(message="country is required")
	private String country;
	
	
	public Employee() {
		super();
	}


	public Employee(long id, String firstName, String lastName, BigDecimal salary, String state, String country) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.salary = salary;
		this.state = state;
		this.country = country;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public BigDecimal getSalary() {
		return salary;
	}


	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", salary=" + salary
				+ ", state=" + state + ", country=" + country + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(country, firstName, id, lastName, salary, state);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(country, other.country) && Objects.equals(firstName, other.firstName) && id == other.id
				&& Objects.equals(lastName, other.lastName) && Objects.equals(salary, other.salary)
				&& Objects.equals(state, other.state);
	}
	
	
	
	
	
	

}
