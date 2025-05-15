package com.offinity.dto;


import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter


public class LeaveRequest {

	// 직원 정보
    private String employeeId;
    private LocalDate employeeDateOfJoin;

    // 연차신청 정보
    private String requestId;
    private LocalDate requestDate;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double leaveDays;
    private String reason;
    private String status;
    private String approvalComment;
    private Boolean updatable;
    private Boolean cancellable;
    private String leaveType;
    private String approverId;
}
