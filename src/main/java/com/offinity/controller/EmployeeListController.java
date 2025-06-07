package com.offinity.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.offinity.dto.EmployeeDTO;
import com.offinity.mapper.EmployeeListMapper;
import com.offinity.mapper.EmployeeListMapper;
import com.offinity.service.EmployeeListService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*")
@RequestMapping("/api/employees")
@RestController
@RequiredArgsConstructor 
public class EmployeeListController {

	private final EmployeeListService employeeListService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getEmployees(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String department,
            @RequestParam(required = false) String position,
            @RequestParam(defaultValue = "employee_id") String sortField,
            @RequestParam(defaultValue = "asc") String sortDir,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        // page, size만 넘기고 서비스가 내부에서 offset 계산
        Map<String, Object> result = employeeListService.getEmployees(
                name, department, position, sortField, sortDir, page, size
        );
        return ResponseEntity.ok(result);
    }
}
