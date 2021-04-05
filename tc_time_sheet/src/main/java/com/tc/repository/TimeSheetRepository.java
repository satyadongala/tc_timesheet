package com.tc.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tc.model.TimeSheetModel;

@Repository
public interface TimeSheetRepository extends JpaRepository<TimeSheetModel, Long> {

	@Query("from TimeSheetModel where (assignmentId.empId.id = :empId or :empId is null) and :from is null and :to is null")
	public List<TimeSheetModel> getEmployeeTimesheets(@Param("empId") Long empId, @Param("from") Date from, @Param("to") Date to);
	
	@Query("from TimeSheetModel where (assignmentId.empId.userName=:userName AND assignmentId.empId.password=:password)")
	public List<TimeSheetModel> getEmployeeInformation(@Param("userName")String userName,@Param("password") String password);

}
