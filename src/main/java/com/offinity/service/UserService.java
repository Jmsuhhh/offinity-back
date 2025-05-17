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
		
		user.setUserRole("ROLE_USER");
		
		userMapper.createUser(user);
	
	}

}
