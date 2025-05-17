package com.offinity.demo;

import org.mybatis.spring.annotation.MapperScan; // 추가한 거 
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
@SpringBootApplication
public class OffinityBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(OffinityBackApplication.class, args);
	}
}
*/

@SpringBootApplication(scanBasePackages = {"com.offinity"})
@MapperScan("com.offinity.mapper")
public class OffinityBackApplication {
    public static void main(String[] args) {
        SpringApplication.run(OffinityBackApplication.class, args);
    }
}

