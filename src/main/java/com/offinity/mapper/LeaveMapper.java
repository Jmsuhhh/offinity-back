package com.offinity.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.offinity.dto.Employee;
import com.offinity.dto.LeaveApprovalDTO;
import com.offinity.dto.LeaveRequest;

@Mapper
public interface LeaveMapper {

	public Employee selectEmployeeById(@Param("employeeId") String employeeId);

	public List<LeaveRequest> selectLeaveRequest(String employeeId);

	public List<Employee> getEmployeeByDepartment(String employeeDepartment);

	public void createLeaveRequests(LeaveRequest lr);

	public void updateLeaveRequest(LeaveRequest lr);

	public void deleteLeaveRequest(String requestId);

	public void updateLeaveApproval(LeaveRequest ula);

	public List<LeaveApprovalDTO> selectPendingApprovalsByApprover(String approverId);

	public int findMaxRequestSeq(String employeeId);

}
