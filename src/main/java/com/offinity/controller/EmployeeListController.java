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
import com.offinity.mapper.EmployeeListMapper;
import com.offinity.service.EmployeeListService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/api/employees")
@RestController
@RequiredArgsConstructor 
public class EmployeeListController {

	private final EmployeeListService employListService;

	@GetMapping
	public ResponseEntity<Map<String, Object>> getEmployees(
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String department,
			@RequestParam(required = false) String position,
			@RequestParam(defaultValue = "id") String sortField,
			@RequestParam(defaultValue = "asc") String sortDir,
			@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "10") int size) {

		int offset = (page - 1) * size;

//		List<EmployeeDTO> employees = employListService.getEmployees(
//				name, department, position, sortField, sortDir, size, offset);

//		int totalCount = employListService.countEmployees(name, department, position);
//
		Map<String, Object> result = new HashMap<>();
//		result.put("employees", employees);
//		result.put("totalCount", totalCount);
//		result.put("totalPages", (int) Math.ceil((double) totalCount / size));
//
		return ResponseEntity.ok(result);
	}
}
