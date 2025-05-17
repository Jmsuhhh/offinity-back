package com.offinity.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class LeaveApplyDTO {

    private String approverId;

    // 연차신청 정보
    private String leaveType;
    private LocalDate requestDate;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double leaveDays;
    private String reason;
	
}
