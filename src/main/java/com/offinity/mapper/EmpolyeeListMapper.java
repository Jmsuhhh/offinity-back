package com.offinity.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.offinity.dto.EmployeeDTO;

@Mapper
public interface EmpolyeeListMapper {
	 /**
     * 필터 및 정렬, 페이징 조건에 맞는 직원 목록을 조회합니다.
     */
    List<EmployeeDTO> selectEmployees(@Param("name") String name,
                                      @Param("department") String department,
                                      @Param("position") String position,
                                      @Param("sortField") String sortField,
                                      @Param("sortDir") String sortDir,
                                      @Param("limit") int limit,
                                      @Param("offset") int offset);

    /**
     * 필터 조건에 맞는 전체 직원 수를 계산합니다.
     */
    int countEmployees(@Param("name") String name,
                       @Param("department") String department,
                       @Param("position") String position);
}
