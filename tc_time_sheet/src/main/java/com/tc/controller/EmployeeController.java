package com.tc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tc.exception.ResourceNotFoundException;
import com.tc.model.EmployeeModel;
import com.tc.repository.EmployeeRepository;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	EmployeeRepository employeeRepository;

	@GetMapping()
	@ResponseBody
	public List<EmployeeModel> getList() {
		List<EmployeeModel> list = new ArrayList<EmployeeModel>();
		list = employeeRepository.findAll();
		return list;
	}

	@PostMapping()
	@ResponseBody
	public EmployeeModel addEmployee(@RequestBody EmployeeModel employeeModel) {
		EmployeeModel employee = employeeRepository.save(employeeModel);
		return employee;
	}

	@GetMapping("/{id}")
	@ResponseBody
	public EmployeeModel getById(@PathVariable(value = "id") Long id) {
		EmployeeModel employeeModel = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee id not found"));
		return employeeModel;
	}

	@PutMapping("/{id}")
	@ResponseBody
	public EmployeeModel empDetailsUpdate(@PathVariable(value = "id") Long id,
			@RequestBody EmployeeModel employeeModel) {
		EmployeeModel employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Id not found"));
		employee.setName(employeeModel.getName());
		employee.setMailId(employeeModel.getMailId());
		employee.setMobile(employeeModel.getMobile());
		employee.setUserName(employeeModel.getUserName());
		employee.setPassword(employeeModel.getPassword());
		EmployeeModel updateData = employeeRepository.save(employee);
		return updateData;
	}
	

}
