package com.offinity.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.offinity.dto.Notice;

@Mapper
public interface NoticeMapper {
	
	// 게시글 전체 조회 메소드
	public List<Notice> selectNotice(String keyword);
	
	// 게시글 단일 조회 메소드 
	public Notice selectNoticeById(Integer noticeId);
	
	// 게시글 생성 메소드
	public void insertNotice(Notice notice);

	// 게시글 수정 메소드
	public void updateNotice(Notice notice);
	
	// 게시글 삭제 메소드
	public void deleteNotice(Integer noticeId);
	
}
