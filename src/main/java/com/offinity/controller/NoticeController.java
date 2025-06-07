package com.offinity.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.offinity.dto.Notice;
import com.offinity.dto.NoticeDTO;
import com.offinity.mapper.NoticeMapper;
import com.offinity.service.NoticeService;

@RestController
public class NoticeController {
	
	NoticeService noticeService;
	
	public NoticeController(NoticeService noticeService) {
		this.noticeService = noticeService;
	}
	
	// 게시글 전체 조회
	@GetMapping("/api/notice")
	public ResponseEntity<List<NoticeDTO>> selectNotice(@RequestParam(name="keyword", required=false) String keyword) {
		
		return ResponseEntity.status(200).body(noticeService.selectNotice(keyword));
	}
	
	// 게시글 단일 조회
	@GetMapping("/api/notice/{noticeId}")
	public ResponseEntity<?> selectNoticeById(@PathVariable(name="noticeId") Integer noticeId) {
		NoticeDTO res = noticeService.selectNoticeById(noticeId);
		
		return ResponseEntity.status(200).body(res);
	}
	
	// 게시글 생성
	@PostMapping("/api/notice")
	public ResponseEntity<?> createNotice(@RequestBody NoticeDTO noticeDto) {
		
		boolean res = noticeService.insertNotice(noticeDto);
		
		if (res) {
			return ResponseEntity.status(200).body("등록 성공");
		} else {
			return ResponseEntity.status(400).body("등록 실패");
		}
		
	}
	
	// 게시글 수정
	@PutMapping("/api/notice/{noticeId}")
	public ResponseEntity<?> updateNotice(@PathVariable(name="noticeId") Integer noticeId, @RequestBody NoticeDTO noticeDto) {
		noticeService.updateNotice(noticeId, noticeDto);
		
		return ResponseEntity.status(200).body("수정 성공");
	}
	
	// 게시글 삭제
	@DeleteMapping("/api/notice/{noticeId}")
	public ResponseEntity<?> deleteNotice(@PathVariable(name="noticeId") Integer noticeId) {
		noticeService.deleteNotice(noticeId);
		
		return ResponseEntity.status(200).body("삭제 성공");
	}
}
