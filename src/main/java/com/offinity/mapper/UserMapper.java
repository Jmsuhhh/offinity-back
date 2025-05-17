package com.offinity.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.offinity.dto.User;

@Mapper
public interface UserMapper {
	
	// 이메일 중복 여부 확인 메소드 
	public Integer isDuplicateEmail(String email);
	
	// 새로운 유저 생성 메소드
	public void createUser(User user);
	
	// id를 통해 user 정보를 가져오는 메소드
	public User getUserByEmail(String email);

}
