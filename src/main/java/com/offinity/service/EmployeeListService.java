package com.offinity.service;

import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class EmployeeListService {
	/**
     * 조건에 맞는 직원 목록과 총 개수, 전체 페이지 수를 반환합니다.
     */
    public Map<String, Object> getEmployees(String name, String department, String position, String sortField, String sortDir, int page, int size) {
		return null;
	}
}
