package com.tc.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

@Entity
@Table(name = "assignment_tbl")
public class AssignmentModel {

	private Long Id;
	private EmployeeModel empId;
	private ManagerModel managerId;
	private Date fromDate;
	private Date toDate;
	private String task;
	private String projectName;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	@ManyToOne
	@JoinColumn(name = "EMP_ID")
	public EmployeeModel getEmpId() {
		return empId;
	}

	public void setEmpId(EmployeeModel empId) {
		this.empId = empId;
	}

	@ManyToOne
	@JoinColumn(name = "MANAGER_ID")
	public ManagerModel getManagerId() {
		return managerId;
	}

	public void setManagerId(ManagerModel managerId) {
		this.managerId = managerId;
	}

	@Column(name = "FROM_DATE")
	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	@Column(name = "TO_DATE")
	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	@Column(name = "TASK")
	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	@Column(name = "PROJECT_NAME")
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	@Override
	public String toString() {
		return "AssignmentModel [Id=" + Id + ", empId=" + empId + ", managerId=" + managerId + ", fromDate=" + fromDate
				+ ", toDate=" + toDate + ", task=" + task + ", projectName=" + projectName + "]";
	}

}
