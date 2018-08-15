package com.ritesh.employee.EmployeeCRUD;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ritesh.employee.EmployeeCRUD.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

	
	
}