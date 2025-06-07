package com.offinity.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "department",required = false) String department,
            @RequestParam(value = "position",required = false) String position,
            @RequestParam(value = "sort-field",defaultValue = "employee_id") String sortField,
            @RequestParam(value = "sort-dir",defaultValue = "asc") String sortDir,
            @RequestParam(value = "page",defaultValue = "1") int page,
            @RequestParam(value = "size",defaultValue = "10") int size

    ) {
        // page, size만 넘기고 서비스가 내부에서 offset 계산
        Map<String, Object> result = employeeListService.getEmployees(
                name, department, position, sortField, sortDir, page, size
        );
        return ResponseEntity.ok(result);
    }
    
    // 수정(PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateEmployee(
            @PathVariable("id") String id,
            @RequestBody EmployeeDTO dto
    ) {
        dto.setEmployeeId(id);
        employeeListService.updateEmployee(dto);
        return ResponseEntity.noContent().build();
    }

    // 삭제(DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable("id") String id) {
        employeeListService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

}
