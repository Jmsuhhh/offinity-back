package com.offinity.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.offinity.dto.CustomUserDetails;
import com.offinity.dto.User;
import com.offinity.mapper.UserMapper;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	UserMapper userMapper;
	
	public CustomUserDetailsService(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	@Override
	public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
		
		System.out.println("로그인 시도 - 이메일: " + userEmail);
		
		// DB에서 사용자 Email로 정보 조회
		User user = userMapper.getUserByEmail(userEmail);
		
		if (user == null) {
			System.out.println("사용자가 존재하지 않습니다.");
		}
		System.out.println("사용자 존재 - 암호화된 비밀번호: " + user.getUserPassword());
		return new CustomUserDetails(user);
	}
	
}
