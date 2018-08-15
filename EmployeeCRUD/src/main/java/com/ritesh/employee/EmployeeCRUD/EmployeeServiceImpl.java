package com.ritesh.employee.EmployeeCRUD;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ritesh.employee.EmployeeCRUD.entity.Employee;


@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepository employeeRepository;
	
	/*@Override
	public void saveEmployee(Employee employee) {
		employeeRepository.saveEmployee(employee);
		
	}*/

}

