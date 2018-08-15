package com.ritesh.employee.EmployeeCRUD.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;




@Entity
@Table(name = "Employee")
public class Employee {

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Emp_id")
	private int id;
	@Column(name = "name")
	@Size(min=2,message = "*Please provide a valid name")
	private String name;
	@Column(name = "phone")
	@Pattern(regexp = "^((\\+|00)(\\d{1,3})[\\s-]?)?(\\d{10})$",message = "*Please provide a valid "
			+ "phone number")
	private String phone;
	@Column(name = "address")
	@NotNull(message = "*Please provide address")
	private String address;
	
	
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}