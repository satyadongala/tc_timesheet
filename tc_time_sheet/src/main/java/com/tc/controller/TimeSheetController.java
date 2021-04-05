package com.tc.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tc.exception.ResourceNotFoundException;
import com.tc.model.TimeSheetModel;
import com.tc.repository.TimeSheetRepository;
import com.tc.service.TimeSheetService;

@RestController
@RequestMapping("/timesheet")
public class TimeSheetController {
	public static String name = "mani";
	@Autowired
	TimeSheetRepository timeSheetRepository;

	@Autowired
	TimeSheetService timeSheetService;

	@GetMapping()
	@ResponseBody
	public List<TimeSheetModel> list() {
		List<TimeSheetModel> list = new ArrayList<TimeSheetModel>();
		list = timeSheetRepository.findAll();
		return list;
	}

	@PostMapping()
	@ResponseBody
	public TimeSheetModel saveData(@RequestBody TimeSheetModel timeSheetModel) {
		TimeSheetModel timeSheet = timeSheetRepository.save(timeSheetModel);

		return timeSheet;
	}

	@GetMapping("/{id}")
	@ResponseBody
	public TimeSheetModel getById(@PathVariable(value = "id") Long id, @RequestBody TimeSheetModel timeSheetModel) {
		TimeSheetModel timeSheet = timeSheetRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Id not found:"));
		return timeSheet;
	}

	@PutMapping("/{id}")
	@ResponseBody
	public TimeSheetModel updateTimeSheet(@PathVariable(value = "id") Long id,
			@RequestBody TimeSheetModel timeSheetModel) {
		TimeSheetModel timeSheet = timeSheetRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Id not found:"));
		timeSheet.setDate(timeSheetModel.getDate());
		timeSheet.setHours(timeSheetModel.getHours());
		timeSheet.setComments(timeSheetModel.getComments());
		TimeSheetModel updateTimeSheet = timeSheetRepository.save(timeSheet);
		return updateTimeSheet;
	}
	


	@GetMapping("/download")
	public ResponseEntity<InputStreamResource> getFile() {
		String filename = "DCBS-DEC-2020.xlsx";
		InputStreamResource file = new InputStreamResource(timeSheetService.load());

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
				.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);
	}
	
	@GetMapping("/list")
	public List<TimeSheetModel> getTimeSheet(@RequestParam(required = false, name="empId") Long empId, @RequestParam(name="from", required=false) Date from, @RequestParam(name="to", required=false) Date to) {
		List<TimeSheetModel> list = timeSheetService.getEmployeeTimeSheet(empId, from, to);
		return list;
	}
	@GetMapping("/login")
	public List<TimeSheetModel> getEmpInfo(@RequestParam(required = true, name="userName") String userName, @RequestParam(name="password", required=true) String password) {
		List<TimeSheetModel> list= timeSheetService.getEmployeeInformation(userName, password);
		return list;
	}

}
