package com.offinity.jwt;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StreamUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.offinity.dto.CustomUserDetails;
import com.offinity.dto.UserDTO;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	AuthenticationManager authenticationManager;
	JwtUtils jwtUtils;
	
	public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
		this.authenticationManager = authenticationManager;
		this.jwtUtils = jwtUtils;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			ServletInputStream inputStream = request.getInputStream();
			String body = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
			
			ObjectMapper objectMapper = new ObjectMapper();
			UserDTO userDto = objectMapper.readValue(body, UserDTO.class);
			
			String email = userDto.getUserEmail();
			String pwd = userDto.getUserPassword();
			
			UsernamePasswordAuthenticationToken userInfo = new UsernamePasswordAuthenticationToken(email, pwd, null);
			
			return authenticationManager.authenticate(userInfo);
			
		} catch(IOException ex) {
			return null;
		}
	}
	
	// 인증 성공 시 실행 
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)
			throws IOException, ServletException {
		
		System.out.println("성공");
		
		// 로그인한 user의 정보 가져오기
		CustomUserDetails userDetails = (CustomUserDetails)authResult.getPrincipal();
		
		String userId = userDetails.getUsername();
		
		Collection<? extends GrantedAuthority> authorities = authResult.getAuthorities();
		String role = null;
		
		for (GrantedAuthority auth : authorities) {
			role = auth.getAuthority();
		}
		
		String token = jwtUtils.createJwt(userId, role, 60*60*100L);
		
		response.addHeader("Authorization", "Bearer " + token);
		
	}
	
	// 인증 실패 시 실행 
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) 
			throws IOException, ServletException {
			
		System.out.println("실패");
		response.setStatus(401);
	}
}
