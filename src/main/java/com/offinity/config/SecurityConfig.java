package com.offinity.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import com.offinity.jwt.JwtAuthenticationFilter;
import com.offinity.jwt.JwtFilter;
import com.offinity.jwt.JwtUtils;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	AuthenticationConfiguration configuration;
	JwtUtils jwtUtils;
	
	public SecurityConfig(AuthenticationConfiguration configuration, JwtUtils jwtUtils) {
		this.configuration = configuration;
		this.jwtUtils = jwtUtils;
	}
	
	@Bean
	public AuthenticationManager getAuthenticationManager(AuthenticationConfiguration configuration) 
	throws Exception {
		return configuration.getAuthenticationManager();
	}
	
	@Bean
	public BCryptPasswordEncoder getBCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean 
	public SecurityFilterChain securityFilterChain(HttpSecurity http)
	throws Exception {
		
		//cors 설정
		http.cors((auth)-> auth.configurationSource( (request)->{
			CorsConfiguration config = new CorsConfiguration();
			         
			// 어디로부터 들어오는 요청을 허용할 것인지
			// VUE의 포트번호를 입력
			config.setAllowedOrigins(Collections.singletonList("http://localhost:8002"));
			// 어떤 요청방식에 대해 허용할 것인지
			// GET, DELETE, ...
			config.setAllowedMethods(Collections.singletonList("*")); // <-- 모든 요청 허용
			// 쿠키를 전달 받을 것인지 설정
			config.setAllowCredentials(true);
			         
			// Header 설정 허용 여부
			config.setAllowedHeaders(Collections.singletonList("*"));
			// 브라우저가 닫혀도 쿠키가 메모리에 남아있는 시간 설정
			config.setMaxAge(3600L); // <-- 1시간
			         
			// 응답헤더 수정 권한
			config.setExposedHeaders(Collections.singletonList("Authorization"));
			         
			return config;
		}));
	
		http.csrf((auth) -> auth.disable());
		
		// 기본으로 뜨는 로그인 화면과 관련된 설정
		http.formLogin((auth) -> auth.disable());
		
		// 요청에 대한 권한 확인
		http.authorizeHttpRequests((auth) -> auth.requestMatchers("/", "/api/login", "/api/signup", "/api/img/**", "/api/find-email", "/api/event/**", "/api/holidays",  "/api/suggestion/**", "/api/employees/**", "/api/calendar/summary", "/api/notice", "/api/notice/**").permitAll()
				.anyRequest().authenticated()
			);
		
		// JwtAuthenticationFilter.class 이전 위치에 JwtFilter를 등록
		http.addFilterBefore(new JwtFilter(jwtUtils), JwtAuthenticationFilter.class);
		
		JwtAuthenticationFilter myFilter = new JwtAuthenticationFilter( getAuthenticationManager(configuration) , jwtUtils );
		myFilter.setFilterProcessesUrl("/api/login");
		
		http.addFilterAt( 
				myFilter , 
				UsernamePasswordAuthenticationFilter.class 
			);
		
		// STATEFUL --> 이전 트랜젝션에 대한 정보를 저장
		// STATELESS --> 이전 트랜젝션에 대한 정보를 저장 X 
		http.sessionManagement((auth)-> auth.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		return http.build();
	}
}
