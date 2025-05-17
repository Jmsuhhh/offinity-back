package com.offinity.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class LeaveApprovalDTO {

	private String requestId;
	private String employeeName;
	private String employeeGrade;
	private LocalDate requestDate;
	private LocalDate startDate;
	private LocalDate endDate;
	private Double leaveDays;
	private String reason;
	private String approvalComment;

}

	
	
