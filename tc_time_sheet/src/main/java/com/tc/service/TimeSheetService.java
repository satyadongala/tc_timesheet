package com.tc.service;

import java.io.ByteArrayInputStream;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tc.excel_exports.ExcelHelper;
import com.tc.model.TimeSheetModel;
import com.tc.repository.TimeSheetRepository;

@Service
public class TimeSheetService {

	@Autowired
	TimeSheetRepository repository;

	public ByteArrayInputStream load() {
		List<TimeSheetModel> list = repository.findAll();
		ByteArrayInputStream in = ExcelHelper.tutorialsToExcel(list);
		return in;
	}
	


	public List<TimeSheetModel> getEmployeeTimeSheet(Long empId, Date from, Date to) {
		List<TimeSheetModel> list = repository.getEmployeeTimesheets(empId, from, to);
		return list;
	}
	public List<TimeSheetModel> getEmployeeInformation(String userName,String password) {
		List<TimeSheetModel> list=repository.getEmployeeInformation(userName, password);
		return list;
	}
}
