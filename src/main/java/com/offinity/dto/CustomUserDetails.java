package com.offinity.dto;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {

	User user;
	
	public CustomUserDetails(User user) {
		this.user = user;
	}
	
	// 로그인 한 user의 권한 파악 메소드
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> list = new ArrayList<>();
		
		GrantedAuthority a = new GrantedAuthority() {
			
			@Override
			public String getAuthority() {
				
				return user.getUserRole();
			}
		}; 
		list.add(a);
		return list;
	}

	// 로그인한 user의 비밀번호로 판단해야 하는 부분이 어디인지 설정 
	@Override
	public String getPassword() {
		return user.getUserPassword();
	}

	@Override
	public String getUsername() {
		return user.getUserId();
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return true;
	}

}
