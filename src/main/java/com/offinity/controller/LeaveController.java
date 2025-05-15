package com.offinity.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.offinity.dto.Approver;
import com.offinity.dto.EmployeeDTO;
import com.offinity.dto.LeaveApplyDTO;
import com.offinity.dto.LeaveRequest;
import com.offinity.dto.LeaveRequestDTO;
import com.offinity.dto.LeaveSummaryDTO;
import com.offinity.service.LeaveService;

@RestController
public class LeaveController {

//	Spring Boot 기반 Rest API로 연차 관련 사항을 보여주고 승인 받도록 하는 컨트롤러 
	
//	GET 	/api/leave/{employeeId}/employee 	해당 직원의 정보를 불러 옴
//	GET 	/api/leave/{employeeId}/summary		해당 직원의 연차 현황 정보를 불러옴
//	GET 	/api/leave/{employeeId}/request  	해당 직원의 연차 신청현황 정보를 불러옴
//	POST 	/api/leave				연차를 신청
//	PUT 	/api/leave/{leaveId}	연차를 수정
//	DELETE 	/api/leave/{leaveId}	연차를 삭제
	
	
//	생성자 주입. LeaveService를 통해 연차 관련 기능 수행
	LeaveService leaveService;
	
    public LeaveController(LeaveService leaveService) {
		this.leaveService = leaveService;
	}

//  사원번호로 직원 정보를 담아옴.   
    
    @GetMapping("/api/leave/{employeeId}/employee")
    public EmployeeDTO getEmployeeById(@PathVariable("employeeId") String employeeId) {
    	
    	System.out.println("컨트롤러");
		return leaveService.getEmployeeById(employeeId);
    }

//  사원번호로 연차현황 정보를 담아옴.  
    @GetMapping("/api/leave/{employeeId}/summary")
    public LeaveSummaryDTO getLeaveSummary(@PathVariable("employeeId") String employeeId) {
    	
    	System.out.println("Summary");
		return leaveService.getLeaveSummary(employeeId);
    }
    
//  사원번호로 연차 신청현황 정보를 담아옴.  
    @GetMapping("/api/leave/{employeeId}/request")
    public List<LeaveRequestDTO> getLeaveRequest(@PathVariable("employeeId") String employeeId) {
    	
    	System.out.println("Request");
		return leaveService.getLeaveRequest(employeeId);
    }
    
//  사원번호로 연차 승인자 정보를 담아옴.  
    @GetMapping("/api/leave/{employeeId}/approver")
    public List<Approver> getLeaveApprover(@PathVariable("employeeId") String employeeId) {
    	
    	System.out.println("Approver");
		return leaveService.getLeaveApprover(employeeId);
    }
    
    
    
//    연차 신청을 저장
    @PostMapping("/api/leave/{employeeId}/apply")
    public void postLeaveApply(@PathVariable("employeeId") String employeeId, @RequestBody LeaveApplyDTO ladto) {
    	
    	System.out.println("Post Leave Apply");
    	leaveService.postLeaveApply(employeeId, ladto);
    }
    
//    연차 신청현황에서 수정할 자료를 선택해서 수정버튼을 누르면 실행
    @PutMapping("/api/leave/{requestId}")
    public ResponseEntity<?> updateLeave(
            @PathVariable("requestId") String requestId,
            @RequestBody LeaveRequestDTO dto) {
        leaveService.updateLeaveRequest(requestId, dto);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/api/leave/{requestId}")
    public void deleteLeave(@PathVariable("requestId") String requestId) {
        leaveService.deleteLeaveRequest(requestId);
    }
    
    
    
    
    
    
    
    
    
}




