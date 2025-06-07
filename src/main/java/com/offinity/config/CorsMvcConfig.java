package com.offinity.config;

//	Spring Boot에서 CORS(Cross-Origin Resource Sharing) 정책을 설정하기 위한 설정 클래스입니다.

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration      
//	해당 클래스가 Spring 설정 클래스임을 명시합니다. Spring 컨테이너는 이 클래스를 로드할 때 내부의 설정을 적용합니다.
public class CorsMvcConfig implements WebMvcConfigurer {

//	implements WebMvcConfigurer:
//	Spring MVC 설정을 커스터마이징할 수 있게 해주는 인터페이스입니다.
//	여러 메서드를 오버라이드하여 Web MVC 동작을 제어할 수 있습니다.
//	여기서는 addCorsMappings 메서드만 오버라이드해서 CORS 설정만 다루고 있습니다.
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedOrigins("http://localhost:8002")
	        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
	        .allowedHeaders("*");  // 필요한 경우: 세션, 쿠키 포함 요청 시
   
//   addMapping("/**")   애플리케이션의 모든 URL 경로에 대해 CORS 정책을 적용
//   allowedOrigins("http://localhost:8002")   해당 출처(origin)에서 오는 요청만 허용. 
//   예: 프론트엔드 서버가 localhost:8002일 때
   
	}
}