package com.tc.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Table(name = "time_sheet_tbl")
public class TimeSheetModel {

	private Long Id;
	private AssignmentModel assignmentId;
	private Date date;
	private int hours;
	private String comments;

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
	@JoinColumn(name = "ASSIGNMENT_ID")
	public AssignmentModel getAssignmentId() {
		return assignmentId;
	}

	public void setAssignmentId(AssignmentModel assignmentId) {
		this.assignmentId = assignmentId;
	}

	@Column(name = "DATE")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "HOURS")
	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	@Column(name = "COMMENTS")
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "TimeSheetModel [Id=" + Id + ", assignmentId=" + assignmentId + ", date=" + date + ", hours=" + hours
				+ ", comments=" + comments + "]";
	}

}
