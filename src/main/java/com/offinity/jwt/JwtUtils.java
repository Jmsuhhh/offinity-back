package com.offinity.jwt;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;

@Component
public class JwtUtils {
	
	SecretKey secretKey;
	
	public JwtUtils(@Value("${spring.jwt.secret}") String secret) {
		this.secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
	}
	
	// 토큰 생성 메소드
	public String createJwt(String userId, String userRole, Long expiredMs) {
		Date startTime = new Date(System.currentTimeMillis());
		Date endTime = new Date(System.currentTimeMillis() + expiredMs);
		
		String jsonWebToken = Jwts.builder()
				.claim("userId", userId) // payload 영역에 사용자 정보 (Id, Role) 담기
				.claim("userRole", userRole)
				.issuedAt(startTime) // 발급 시간
				.expiration(endTime) // 만료 시간
				.signWith(secretKey) 
				.compact(); // Jwt 문자열로 반환
		
		System.out.println("생성된 토큰: " + jsonWebToken);
		System.out.println("expired at: " + endTime);
		
		return jsonWebToken;
	}

	// 토큰에서 userId 꺼내오는 메소드
	public String getUserId(String token) {
		return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("userId", String.class);
		
	}
	
	// 토큰에서 userRole 꺼내오는 메소드
	public String getUserRole(String token) {
		return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("userRole", String.class);
	}
	
	// 만료된 토큰인지 확인하는 메소드
	public boolean isExpired(String token) {
		return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());
	}
}
