package com.offinity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@MapperScan("com.offinity.mapper")					// Mapper 설정을 명확히 하기 위함. 
@ComponentScan(basePackages = "com.offinity")		// 디렉토리 설정(OffinityBackApplication이 같은 레벨의 디렉토리로 있을 때
public class OffinityBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(OffinityBackApplication.class, args);
	}

}
