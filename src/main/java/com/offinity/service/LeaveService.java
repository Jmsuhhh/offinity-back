package com.offinity.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.offinity.OffinityBackApplication;
import com.offinity.controller.EventController;
import com.offinity.dto.ApproverDTO;
import com.offinity.dto.Employee;
import com.offinity.dto.EmployeeDTO;
import com.offinity.dto.LeaveApplyDTO;
import com.offinity.dto.LeaveApprovalDTO;
import com.offinity.dto.LeaveConfirmDTO;
import com.offinity.dto.LeaveRequest;
import com.offinity.dto.LeaveRequestDTO;
import com.offinity.dto.LeaveSummaryDTO;
import com.offinity.mapper.LeaveMapper;

@Service
public class LeaveService {

    private final EventController eventController;

    private final OffinityBackApplication offinityBackApplication;



//	생성자 주입
	@Autowired
	private LeaveMapper leaveMapper;
	
	public LeaveService(LeaveMapper leaveMapper, OffinityBackApplication offinityBackApplication, EventController eventController) {
		this.leaveMapper = leaveMapper;
		this.offinityBackApplication = offinityBackApplication;
		this.eventController = eventController;
	}

	
//	직원 정보를 employeeId로 불러와서 프론트로 전달
	
	public EmployeeDTO getEmployeeById(String employeeId) {
	
		Employee e = leaveMapper.selectEmployeeById(employeeId);
		
//		만일 employeeId가 존재하지 않으면  null 반환
		if(e == null) {
			System.out.println("데이터가 없음1");
			return null;
		}
		
		EmployeeDTO dto = new EmployeeDTO();
		dto.setEmployeeId(e.getEmployeeId());
	    dto.setEmployeeName(e.getEmployeeName());
		dto.setEmployeeBirthday(e.getEmployeeBirthday());
	    dto.setEmployeeDateOfJoin(e.getEmployeeDateOfJoin());
	    dto.setEmployeeGrade(e.getEmployeeGrade());
	    dto.setEmployeeDepartment(e.getEmployeeDepartment());
	    dto.setEmployeeDepartmentHead(e.isEmployeeDepartmentHead());	// boolean 인 경우 get 아닌 is를 사용
	    dto.setEmployeeAddress(e.getEmployeeAddress());
	    dto.setEmployeeContact(e.getEmployeeContact());
	    dto.setEmployeeEmail(e.getEmployeeEmail());   
		
	    System.out.println("직원 정보 DB에서 읽고 프론트로");
	    
		return dto;
	}

//	직원 연차 현황을 요약해서 프론트로 전달
	
	public LeaveSummaryDTO getLeaveSummary(String employeeId) {

		Employee e = leaveMapper.selectEmployeeById(employeeId);	// 직원 정보를 e에 담아줌.
		
		List<LeaveRequest> requests = leaveMapper.selectLeaveRequest(employeeId);
		
		Integer total = calculateTotalLeave(e.getEmployeeDateOfJoin());	// 총연차를 계산
		Double usedLeave = calculateUsedLeave(requests);
		Double residualLeave = total - usedLeave;
				
		
		System.out.println("총연차: "+total+"사용연차: "+usedLeave+"잔여연차: "+residualLeave );
		
		LeaveSummaryDTO lsdto = new LeaveSummaryDTO();				// 연차 요약 정보를 담아줄 class
		
		lsdto.setEmployeeId(e.getEmployeeId());
		lsdto.setTotalLeave(total);
		lsdto.setUsedLeave(usedLeave);
		lsdto.setResidualLeave(residualLeave);
		
		return lsdto;
	}

//	연차 신청현황을 읽어서 프론트로 전달
	
	public List<LeaveRequestDTO> getLeaveRequest(String employeeId) {
		
		List<LeaveRequest> lr = leaveMapper.selectLeaveRequest(employeeId);
		
//		만일 LeaveRequest가 존재하지 않으면  null 반환
		if(lr == null) {
			System.out.println("데이터가 없음2");
			return null;
		}

		List<LeaveRequestDTO> dto = new ArrayList<>();
		
		for(LeaveRequest leave : lr) {
			
		    if (leave.getRequestId() == null) {
		        System.out.println("연차 신청 없음: 직원 ID = " + leave.getEmployeeId());
		        return null;
		    } else {

		    	System.out.println("신청 있음: ID = " + leave.getRequestId());		    	
				LeaveRequestDTO dtoTemp = new LeaveRequestDTO();
		        
			    dtoTemp.setEmployeeId(leave.getEmployeeId());
			    dtoTemp.setEmployeeDateOfJoin(leave.getEmployeeDateOfJoin());
				
			    dtoTemp.setRequestId(leave.getRequestId());
			    dtoTemp.setRequestDate(leave.getRequestDate());
			    dtoTemp.setStartDate(leave.getStartDate());
			    dtoTemp.setEndDate(leave.getEndDate());
			    dtoTemp.setLeaveDays(leave.getLeaveDays());
			    dtoTemp.setReason(leave.getReason());
			    dtoTemp.setStatus(leave.getStatus());
			    dtoTemp.setApprovalComment(leave.getApprovalComment());
			    dtoTemp.setUpdatable(leave.getUpdatable());
			    dtoTemp.setCancellable(leave.getCancellable());
			    dtoTemp.setLeaveType(leave.getLeaveType());
			    dtoTemp.setApproverId(leave.getApproverId());
		        dto.add(dtoTemp);
		        
		        
		        System.out.println(leave.getApproverId());
		    }
		}
		
		System.out.println("연차신청 정보 DB에서 읽고 프론트로");	
		
		return dto;
	}
	
	
	
//	총연차 계산 로직
	
	private int calculateTotalLeave(LocalDate joinDate) {
	    if (joinDate == null) return 0;

	    LocalDate now = LocalDate.now();
	    Period period = Period.between(joinDate, now);		// 두 일자 간의 연, 월, 일 차이를 계산
	    int years = period.getYears();						// 두 일자 간의 연 차이 
	    int months = period.getMonths();					// 두 일자 간의 월 차이

	    // 1년 미만인 경우: 매월 1일씩 (최대 11일)
	    if (years == 0) {
	        int totalMonths = (int) ChronoUnit.MONTHS.between(joinDate.withDayOfMonth(1), now.withDayOfMonth(1));
	        		// ChronoUnit.MONTHS.between(시작월의 날자를 1일로, 종료일의 날자를 1일로) 두 날자의 개월 수를 산정
	        return Math.min(totalMonths, 11);	// 둘 중 작은 값을 반환
	    }

	    // 1년 이상인 경우: 15일 + 추가일수 (3년차부터 시작, 매 2년마다 1일씩)
	    int extra = ((years - 3) / 2 + 1); // 예: 5년 미만 +1, 5년 이상 +2
	    return Math.min(15 + Math.max(extra, 0), 25);
	}
	
//	사용한 연차 계산
    public Double calculateUsedLeave(List<LeaveRequest> requests) {
        // 1. null 또는 비어있는 리스트이면 0.0 반환
        if (requests == null || requests.isEmpty()) {
            return 0.0;
        }

        // 2. requestId가 없는 경우 0 반환 (단일 데이터 기준)
        if (requests.size() == 1 && (requests.get(0).getRequestId() == null || requests.get(0).getRequestId().isEmpty())) {
            return 0.0;
        }

        // 3. 조건에 맞는 leaveDays 합산
        
        Double result = 0.0;
		for(LeaveRequest leave : requests) {
			if(leave.getStatus().equals("APPROVED")) {			// 결재자가 승인한 건만 계산
				if(leave.getEndDate().isBefore(LocalDate.now())) {
					result += leave.getLeaveDays();
				}
			}
        	System.out.println(leave.getLeaveDays()+" "+leave.getRequestId());
		}
        return result;
    }

//  연차신청을 프론트에서 받아서 DB에 저장
	public void postLeaveApply(String employeeId, LeaveApplyDTO ladto) {
		
		
		Employee e = leaveMapper.selectEmployeeById(employeeId);	// 직원 정보 불러오기
		
//		연차 신청한 회수 계산
		int maxSeq = leaveMapper.findMaxRequestSeq(employeeId);  // 기존 최댓값
	    String requestId = e.getEmployeeId() + "-" + String.format("%03d", maxSeq + 1);

//		연차 신청한 정보를 연차신청현황에 저장
		
		LeaveRequest lr = new LeaveRequest();

		// 연차 직원정보
		lr.setEmployeeId(e.getEmployeeId());
	    lr.setEmployeeDateOfJoin(e.getEmployeeDateOfJoin());

	    // 연차 신청정보
	    lr.setLeaveType(ladto.getLeaveType());
	    lr.setRequestId(requestId);
	    lr.setRequestDate(ladto.getRequestDate());
	    lr.setStartDate(ladto.getStartDate());
	    lr.setEndDate(ladto.getEndDate());
	    lr.setLeaveDays(ladto.getLeaveDays());
	    lr.setReason(ladto.getReason());
	    lr.setStatus("PENDING");
	    lr.setApprovalComment("");
	    lr.setUpdatable(true);
	    lr.setCancellable(true);
	    lr.setApproverId(ladto.getApproverId());

	    System.out.println("연차 신청 작업 수행 중");
	    
	    leaveMapper.createLeaveRequests(lr);
		
	}

	
//	employeeId의 부서 결재자 정보를 읽어서 프론트로 전달
	public List<ApproverDTO> getLeaveApprover(String employeeId) {
		
//		같은 부서 부서장 이름 불러오기
		Employee e = leaveMapper.selectEmployeeById(employeeId);	// 직원 정보 불러오기
		List<Employee> le = leaveMapper.getEmployeeByDepartment(e.getEmployeeDepartment());
																	// 직원 부서원 직원 정보들을 불러오기
		List<ApproverDTO> approver = new ArrayList<>();		// 부서 결재권자, 여러명이 있는 경우 List로 저장
		
		System.out.println("결재자 정보");
		
		for(Employee eTEMP : le) {
			if(eTEMP.getEmployeeGrade().equals("부장") || eTEMP.getEmployeeGrade().equals("과장")) {
				ApproverDTO aTEMP = new ApproverDTO();
				aTEMP.setApproverId(eTEMP.getEmployeeId());
				aTEMP.setApproverName(eTEMP.getEmployeeName());
				approver.add(aTEMP);
			}
		}
		
		
		return approver;
	}

//	프론트에서 수정요청한 것으로 DB 수정
	public void updateLeaveRequest(String requestId, LeaveRequestDTO u) {
	    
		if (u == null) {
		    System.out.println("요청 본문이 바인딩되지 않았습니다 (u is null)");
		}
		
		LeaveRequest lr = new LeaveRequest();
		
//		lr.setEmployeeId(u.getEmployeeId());
//		lr.setEmployeeDateOfJoin(u.getEmployeeDateOfJoin());

	    // 연차신청 정보
	    lr.setRequestId(requestId);
	    lr.setApproverId(u.getApproverId());
	    lr.setLeaveType(u.getLeaveType());
	    lr.setRequestDate(u.getRequestDate());
	    lr.setStartDate(u.getStartDate());
	    lr.setEndDate(u.getEndDate());
	    lr.setLeaveDays(u.getLeaveDays());
	    lr.setReason(u.getReason());
//	    lr.setStatus(u.getStatus());
//	    lr.setApprovalComment(u.getApprovalComment());
//	    lr.setUpdatable(u.getUpdatable());
//	    lr.setCancellable(u.getCancellable());


		
	    System.out.println(u.getApproverId());	
	    System.out.println(u.getLeaveType());	
	    System.out.println(u.getRequestDate());	
	    System.out.println(u.getStartDate());	
	    System.out.println(u.getEndDate());
	    System.out.println(u.getLeaveDays());
	    System.out.println(u.getReason());
	    
//	    연차신청 현황에서 수정할 내용 선택하여 수정
	    System.out.println("연차신청 내용 수정");
	    leaveMapper.updateLeaveRequest(lr);
	}

	public void deleteLeaveRequest(String requestId) {
//	    연차신청 현황에서 취소할 내용 선택하여 취소
		System.out.println("연차신청 내용 취소");
	    leaveMapper.deleteLeaveRequest(requestId);
	}


	public void updateApproval(String requestId, LeaveConfirmDTO lcDTO) {

		LeaveRequest ula = new LeaveRequest();		// Update Leave by Approval 
		ula.setRequestId(requestId);
		ula.setStatus(lcDTO.getStatus());
		ula.setApprovalComment(lcDTO.getApprovalComment());
		ula.setUpdatable(false);     // 결재 후 수정 불가
		ula.setCancellable(false);   // 결재 후 취소 불가

		leaveMapper.updateLeaveApproval(ula);
	}

	public List<LeaveApprovalDTO> getPendingApprovals(String approverId) {
		
		List<LeaveApprovalDTO> dto = leaveMapper.selectPendingApprovalsByApprover(approverId);

		
		System.out.println("결재자 정보를 불러옴");
		System.out.println(approverId);
		
		
		if(dto == null) {
			System.out.println("데이터가 없음2");
			return null;
		}
		
		return dto;
	}

	
}


