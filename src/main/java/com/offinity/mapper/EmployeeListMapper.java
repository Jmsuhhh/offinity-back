package com.offinity.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.offinity.dto.EmployeeDTO;

import lombok.RequiredArgsConstructor;

@Mapper 
public interface EmployeeListMapper {
	/**
     * 검색, 정렬, 페이징 조건에 맞춰 직원 목록을 조회합니다.
     * @param name 이름 필터
     * @param department 부서 필터
     * @param position 직책 필터
     * @param sortField 정렬할 컬럼명 (예: employee_id)
     * @param sortDir 정렬 방향 ("asc" 또는 "desc")
     * @param limit 가져올 레코드 수
     * @param offset 조회 시작 위치
     * @return EmployeeDTO 리스트
     */
    List<EmployeeDTO> selectEmployees(
            @Param("name") String name,
            @Param("department") String department,
            @Param("position") String position,
            @Param("sortField") String sortField,
            @Param("sortDir") String sortDir,
            @Param("limit") int limit,
            @Param("offset") int offset
    );

    /**
     * 검색 조건에 맞는 전체 직원 수를 조회합니다.
     * @param name 이름 필터
     * @param department 부서 필터
     * @param position 직책 필터
     * @return 총 직원 수
     */
    int countEmployees(
            @Param("name") String name,
            @Param("department") String department,
            @Param("position") String position
    );

    /**
     * 직원 정보를 수정합니다.
     * @param dto 수정할 직원 정보
     */
    void updateEmployee(EmployeeDTO dto);

    /**
     * 직원 정보를 삭제합니다.
     * @param id 삭제할 직원 ID
     */
    void deleteEmployee(@Param("id") String id);
}
