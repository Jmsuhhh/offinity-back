package com.offinity.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter


public class Employee {
	
	private String employeeId;
	private String employeeName;
    private String employeeBirthday;
    private LocalDate employeeDateOfJoin;
    private String employeeGrade;
    private String employeeDepartment;
    private boolean employeeDepartmentHead;
    private String employeeAddress;
    private String employeeContact;
    private String employeeEmail;
    private String employeePhoneNumber;
    private String employeePosition;

    

}
