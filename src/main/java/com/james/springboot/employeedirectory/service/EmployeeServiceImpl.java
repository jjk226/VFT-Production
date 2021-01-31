package com.james.springboot.employeedirectory.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.james.springboot.employeedirectory.dao.EmployeeRepository;
import com.james.springboot.employeedirectory.entity.Employee;


@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
	private EmployeeRepository employeeRepository;
	
	@Autowired
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) { 
		this.employeeRepository = employeeRepository;
	}

	@Override
	@Transactional
	public List<Employee> findAll() {
		return employeeRepository.findAllByOrderByLastNameAsc();
	}

	@Override
	@Transactional
	public Employee findById(int id) {
		Optional<Employee> result = employeeRepository.findById(id);
		Employee tempEmployee = null;
		if (result.isPresent()) {
			tempEmployee = result.get();
		} else {
			throw new RuntimeException("Employee not found.");
		}
		return tempEmployee;
	}

	@Override
	@Transactional
	public void save(Employee employee) {
		employeeRepository.save(employee);
	}

	@Override
	@Transactional
	public void deleteById(int id) {
		employeeRepository.deleteById(id);
	}

}
