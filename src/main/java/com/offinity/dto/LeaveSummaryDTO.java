package com.offinity.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class LeaveSummaryDTO {

	private String employeeId;
    private Integer totalLeave;
    private Double usedLeave;   
    private Double residualLeave;
}
