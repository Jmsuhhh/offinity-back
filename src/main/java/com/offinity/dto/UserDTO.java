package com.offinity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO { // 프론트와 소통 
	
	private String userId;
	private String userName;
	private String userEmail;
	private String userPassword;
	private String userSecurityQuestion;
	private String userSecurityAnswer;
	private String userRole;
	
}
