package com.offinity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.offinity.dto.User;
import com.offinity.dto.UserDTO;
import com.offinity.mapper.UserMapper;

@Service
public class UserService {
	
	BCryptPasswordEncoder encoder;
	UserMapper userMapper;
	
	@Autowired
	public UserService(BCryptPasswordEncoder encoder, UserMapper userMapper) {
		this.encoder = encoder;
		this.userMapper = userMapper;
	}
	
	public boolean isDuplicateEmail(String email) {
		Integer res = userMapper.isDuplicateEmail(email);
		if (res > 0) {
			return false;
		}
		else 
			return true;
	}
	
	public void signUpProcess(UserDTO userDto) {
		
		// 이메일 중복 검사
		if (isDuplicateEmail(userDto.getUserEmail())) {
			System.out.println("이미 사용 중인 이메일입니다.");
		}
		
		User user = new User();
		
		user.setUserId(userDto.getUserId());
		user.setUserName(userDto.getUserName());
		user.setUserEmail(userDto.getUserEmail());
		
		user.setUserPassword(encoder.encode(userDto.getUserPassword()));
		
		user.setUserSecurityQuestion(userDto.getUserSecurityQuestion());
		user.setUserSecurityAnswer(userDto.getUserSecurityAnswer());
		
		user.setUserRole("ROLE_ADMIN");
		
		userMapper.createUser(user);
	
	}
	
	public boolean isUserExist(String name) {
		Integer res = userMapper.isUserExist(name);
		if (res > 0) {
			return true;
		}
		else 
			return false;
		
	}
	
	public String findEmailProcess(UserDTO userDto) {
		
		if (!isUserExist(userDto.getUserName())) {
			System.out.println("존재하지 않는 사용자입니다.");
			return null;
		}
		else {
			
			User user = userMapper.getUserByName(userDto.getUserName());
			
			if (user.getUserSecurityQuestion().equals(userDto.getUserSecurityQuestion()) &&
		            user.getUserSecurityAnswer().equals(userDto.getUserSecurityAnswer())) {
				return user.getUserEmail();
			} else {
				System.out.println("본인 확인에 실패했습니다.");
				return null;
			}
			
			
		}
	}

}
