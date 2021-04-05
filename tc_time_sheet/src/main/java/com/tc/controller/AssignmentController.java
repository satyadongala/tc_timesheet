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
import com.tc.model.AssignmentModel;
import com.tc.repository.AssignmentRepository;

@RestController
@RequestMapping("/assignment")
public class AssignmentController {
	@Autowired
	AssignmentRepository assignmentRepository;

	@GetMapping()
	@ResponseBody
	public List<AssignmentModel> getList() {
		List<AssignmentModel> list = new ArrayList<AssignmentModel>();
		list = assignmentRepository.findAll();
		return list;
	}

	@PostMapping()
	@ResponseBody
	public AssignmentModel addAssignment(@RequestBody AssignmentModel assignmentModel) {
		AssignmentModel assignment = assignmentRepository.save(assignmentModel);
		return assignment;
	}

	@GetMapping("/{id}")
	@ResponseBody
	public AssignmentModel getById(@PathVariable(value = "id") Long id) {
		AssignmentModel assignmentModel = assignmentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Id not found:"));
		return assignmentModel;
	}

	@PutMapping("/{id}")
	@ResponseBody
	public AssignmentModel updateAssignment(@PathVariable(value = "id") Long id,
			@RequestBody AssignmentModel assignmentModel) {
		AssignmentModel assignment = assignmentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Id not found:"));
		assignment.setFromDate(assignmentModel.getFromDate());
		assignment.setToDate(assignmentModel.getToDate());
		assignment.setProjectName(assignmentModel.getProjectName());
		assignment.setTask(assignmentModel.getTask());
		AssignmentModel updateAssignment = assignmentRepository.save(assignment);
		return updateAssignment;
	}

}
