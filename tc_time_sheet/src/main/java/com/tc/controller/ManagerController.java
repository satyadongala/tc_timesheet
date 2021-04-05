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
import com.tc.model.ManagerModel;
import com.tc.repository.ManagerRepository;

@RestController
@RequestMapping("/manager")
public class ManagerController {

	@Autowired
	ManagerRepository managerRepository;

	@GetMapping()
	@RequestMapping
	public List<ManagerModel> list() {
		List<ManagerModel> list = new ArrayList<>();
		list = managerRepository.findAll();
		return list;
	}
	@PostMapping()
	@ResponseBody
	public ManagerModel addManager(@RequestBody ManagerModel managerModel) {
		ManagerModel addManager=managerRepository.save(managerModel);
		return addManager;
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public ManagerModel getById(@PathVariable(value="id")Long id) {
		 ManagerModel manager=managerRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Id Is Not Found"));
		return manager;
	}
	@PutMapping("/{id}")
	@ResponseBody
	public ManagerModel updateManagerDetails(@PathVariable(value="id")Long id,ManagerModel managerModel) {
		ManagerModel manager=managerRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Id Is Not Found"));
		manager.setName(managerModel.getName());
		manager.setMailId(managerModel.getMailId());
		ManagerModel updateManager=managerRepository.save(manager);
		return updateManager;
	}
	
}
