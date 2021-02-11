package com.james.springboot.vftproductiontracker.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.james.springboot.vftproductiontracker.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	public List<Employee> findAllByOrderByLastNameAsc();

}
