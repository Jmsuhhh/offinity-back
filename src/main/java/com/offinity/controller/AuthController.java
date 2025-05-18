package com.offinity.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.offinity.dto.UserDTO;
import com.offinity.service.UserService;

@RestController
public class AuthController {
	
	UserService userService;
	
	public AuthController(UserService userService) {
		this.userService = userService;
	}
	
	// 이메일 중복 여부 확인 
	@GetMapping("/api/signup")
	public boolean duplicateEmail(@RequestParam(name = "email") String email) {
		return userService.isDuplicateEmail(email);
	}
	
	// 회원 가입 
	@PostMapping("/api/signup") 
	public String signUpProcess(@RequestBody UserDTO userDto) {
		userService.signUpProcess(userDto);
		return "성공";
	}
	
	// 이메일 찾기
	@PostMapping("/api/find-email")
	public ResponseEntity<String> findEmail(@RequestBody UserDTO userDto) {
		String email = userService.findEmailProcess(userDto);
		
		if (email == null) {
			return ResponseEntity.badRequest().body("이메일을 찾을 수 없습니다.");
		}
		else {
			return ResponseEntity.ok(email);
		}
	}
  
}
