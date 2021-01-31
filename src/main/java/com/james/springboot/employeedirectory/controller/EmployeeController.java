package com.james.springboot.employeedirectory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.james.springboot.employeedirectory.entity.Employee;
import com.james.springboot.employeedirectory.service.EmployeeService;

import javax.validation.Valid;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	
	private EmployeeService employeeService;


	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@GetMapping("/homepage")
	public String showHomepage() {
		
		return "employees/homepage";
	}
	
	@GetMapping("/list")
	public String getEmployees(Model model) {
		List<Employee> employees = employeeService.findAll();
		model.addAttribute("employees", employees);

		return "employees/list-employees";
	}
	
	@GetMapping("/add")
	public String getEmployeeAddForm(Model model) {
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		
		return "employees/add-employee-form";
	}
	
	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") @Valid Employee employee, Error error, Model model) {
		if (error.equals(true)) {
			System.out.println(error.getMessage());
			model.addAttribute("employee", employee);
			return "redirect:/error";
		}
		this.employeeService.save(employee);
		
		return "redirect:/employees/list";
	}
	
	@GetMapping("/updateEmployeeForm")
	public String updateEmployeeForm(@RequestParam("employeeId") int id, Model model) {
		Employee employee = this.employeeService.findById(id);
		
		model.addAttribute("employee", employee);
		
		return "employees/add-employee-form";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("employeeId") int id) {
		this.employeeService.deleteById(id);
		
		return "redirect:/employees/list";
	}

}
