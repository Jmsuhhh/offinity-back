package com.offinity;

import org.mybatis.spring.annotation.MapperScan; // 추가한 거 
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication (scanBasePackages = {"com.offinity"})
@MapperScan("com.offinity.mapper")

public class OffinityBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(OffinityBackApplication.class, args);
	}
}
