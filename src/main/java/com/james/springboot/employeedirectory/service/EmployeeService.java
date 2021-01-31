package com.james.springboot.employeedirectory.service;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.james.springboot.employeedirectory.entity.Employee;


public interface EmployeeService {
	
	
	public List<Employee> findAll();

	public Employee findById(int id);

	public void save(Employee employee);

	public void deleteById(int id);
	

}
