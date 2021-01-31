package com.james.springboot.employeedirectory.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.james.springboot.employeedirectory.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	public List<Employee> findAllByOrderByLastNameAsc();

}
