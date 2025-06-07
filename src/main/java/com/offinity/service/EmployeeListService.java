package com.offinity.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.offinity.dto.EmployeeDTO;
import com.offinity.mapper.EmployeeListMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeListService {
	 private final EmployeeListMapper mapper;

	    
	    public Map<String, Object> getEmployees(
	            String name,
	            String department,
	            String position,
	            String sortField,
	            String sortDir,
	            int page,
	            int size
	    ) {
	        // 1) offset 계산
	        int offset = (page - 1) * size;
	        
	        // 2) 매퍼 호출: 필터+정렬+페이징된 목록 조회
	        List<EmployeeDTO> list = mapper.selectEmployees(
	                name, department, position,
	                sortField, sortDir,
	                size, offset
	        );

	        // 3) 총 개수 조회
	        int totalCount = mapper.countEmployees(name, department, position);

	        // 4) 결과 조립
	        Map<String, Object> result = new HashMap<>();
	        result.put("employees", list);
	        result.put("totalCount", totalCount);
	        result.put("totalPages", (int) Math.ceil((double) totalCount / size));
	        return result;
	    }

	   
	    public void updateEmployee(EmployeeDTO dto) {
	        mapper.updateEmployee(dto);
	    }

	   
	    public void deleteEmployee(String id) {
	        mapper.deleteEmployee(id);
	    }
}
