package com.offinity.jwt;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.offinity.dto.CustomUserDetails;
import com.offinity.dto.User;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtFilter extends OncePerRequestFilter {
	
	JwtUtils jwtUtils;
	
	public JwtFilter(JwtUtils jwtUtils) {
		this.jwtUtils = jwtUtils;
	}

	// 필터를 어떻게 동작시키려고 하는 건지
	// 요청과 관련된 정보는 매개변수 'request'에 들어있음
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String authToken = request.getHeader("Authorization"); // request 안의 헤더로부터 문자열을 꺼낼 것

		String path = request.getRequestURI();
		
		System.out.println("📌 요청 URI: " + path);
		
	    if (path.equals("/api/signup") || path.equals("/api/login")||
	    	    path.startsWith("/api/event") ||
	    	    path.startsWith("/api/holidays") 
	    		) {
	    	
	        filterChain.doFilter(request, response);
	        return;
	    }
	    
		if (authToken == null || !authToken.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		System.out.println("JWT 검증 시작");
		
		String jwt = authToken.split(" ")[1]; // 순수 JWT
		
		if (jwtUtils.isExpired(jwt)) {
			System.out.println("만료된 토큰");
			filterChain.doFilter(request, response);
			return;
		}
		
		// v 정상 토큰
		User user = new User();
		user.setUserId(jwtUtils.getUserId(jwt));
		user.setUserRole(jwtUtils.getUserRole(jwt));
		
		CustomUserDetails customUserDetails = new CustomUserDetails(user);
		
		// 매개변수 --> (유저에대한정보, 비밀번호와관련된정보, 권한과관련된정보)
		Authentication auth = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
		
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		filterChain.doFilter(request, response);
		
	}

}
