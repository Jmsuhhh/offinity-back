package com.offinity.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.offinity.dto.Notice;
import com.offinity.dto.NoticeDTO;
import com.offinity.mapper.NoticeMapper;

@Service
public class NoticeService {
	
	NoticeMapper noticeMapper;
	
	@Autowired
	public NoticeService(NoticeMapper noticeMapper) {
		this.noticeMapper = noticeMapper;
	}
	
	// 게시글 전체 조회 메소드
	public List<NoticeDTO> selectNotice(String keyword) {
		List<Notice> noticeListFromDB = noticeMapper.selectNotice(keyword);
		List<NoticeDTO> noticeListToFont = new ArrayList<>();
		
		for (Notice n : noticeListFromDB) {
			NoticeDTO dto = new NoticeDTO();
			
			dto.setNoticeId(n.getNoticeId());
			dto.setWriter(n.getWriter());
			dto.setTitle(n.getTitle());
			dto.setContent(n.getContent());
			dto.setCreateDate(n.getCreateDate());
			dto.setUpdateDate(n.getUpdateDate());
			
			noticeListToFont.add(dto);
		}
		
		return noticeListToFont;
	}
	
	// noticeId를 통해 게시글 하나를 조회하는 메소드
	public NoticeDTO selectNoticeById(Integer noticeId) {
		Notice notice = noticeMapper.selectNoticeById(noticeId);
		
		NoticeDTO dto = new NoticeDTO();
		dto.setNoticeId(notice.getNoticeId());
		dto.setWriter(notice.getWriter());
		dto.setTitle(notice.getTitle());
		dto.setContent(notice.getContent());
		dto.setCreateDate(notice.getCreateDate());
		dto.setUpdateDate(notice.getUpdateDate());
		
		return dto;
	}
	
	// 게시글 생성 메소드
	public boolean insertNotice(NoticeDTO noticeDto) {
		Notice notice = new Notice();
		
		notice.setWriter(noticeDto.getWriter());
		notice.setTitle(noticeDto.getTitle());
		notice.setContent(noticeDto.getContent());
		notice.setCreateDate(LocalDateTime.now());
		notice.setUpdateDate(LocalDateTime.now());
		
		noticeMapper.insertNotice(notice);
		
		return true;
	}
	
	// 게시글 수정 메소드
	public void updateNotice(Integer noticeId, NoticeDTO noticeDto) {
		Notice notice = new Notice();
		
		notice.setNoticeId(noticeId);
		notice.setTitle(noticeDto.getTitle());
		notice.setContent(noticeDto.getContent());
		notice.setUpdateDate(LocalDateTime.now());
		
		noticeMapper.updateNotice(notice);
	}
	
	// 게시글 삭제 메소드
	public void deleteNotice(Integer noticeId) {
		noticeMapper.deleteNotice(noticeId);
	}



	

}
