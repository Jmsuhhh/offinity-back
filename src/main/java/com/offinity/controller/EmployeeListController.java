package com.offinity.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.offinity.dto.EmployeeDTO;
import com.offinity.mapper.EmployeeListMapper;
import com.offinity.mapper.EmpolyeeListMapper;
import com.offinity.service.EmployeeListService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/api/employees")
@RestController
@RequiredArgsConstructor
public class EmployeeListController {
	 private final EmployeeListService employListService;

	    /**
	     * 직원 목록을 조건에 따라 조회하고, 페이징 정보를 포함해 반환합니다.
	     */
	    public Map<String, Object> getEmployees(String name, String department, String position,
	                                            String sortField, String sortDir, int page, int size) {
	        int offset = (page - 1) * size;

	        // 직원 목록 조회
	        List<EmployeeDTO> employees = EmployeeListMapper.selectEmployees(
	                name, department, position, sortField, sortDir, size, offset
	        );

	        // 전체 직원 수 조회
	        int totalCount = EmployeeListMapper.countEmployees(name, department, position);

	        // 결과 데이터 구성
	        Map<String, Object> result = new HashMap<>();
	        result.put("employees", employees);
	        result.put("totalCount", totalCount);
	        result.put("totalPages", (int) Math.ceil((double) totalCount / size));

	        return result;
	    }
}
