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

	    /**
	     * 조건에 맞는 직원 목록과 페이징 정보를 반환합니다.
	     *
	     * @param name       이름 필터
	     * @param department 부서 필터
	     * @param position   직책 필터
	     * @param sortField  정렬 필드
	     * @param sortDir    정렬 방향 (asc/desc)
	     * @param page       페이지 번호 (1부터 시작)
	     * @param size       페이지당 항목 수
	     * @return employees: 목록, totalCount: 전체 개수, totalPages: 전체 페이지 수
	     */
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

	    /**
	     * 직원 정보를 수정합니다.
	     *
	     * @param dto 수정할 직원 데이터 (employeeId 포함)
	     */
	    public void updateEmployee(EmployeeDTO dto) {
	        mapper.updateEmployee(dto);
	    }

	    /**
	     * 직원 레코드를 삭제합니다.
	     *
	     * @param id 삭제할 직원 ID
	     */
	    public void deleteEmployee(String id) {
	        mapper.deleteEmployee(id);
	    }
}
