package com.offinity.service;

import com.offinity.dto.SuggestionBoard;
import com.offinity.mapper.SuggestionBoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SuggestionBoardService {

	@Autowired
    private SuggestionBoardMapper suggestionBoardMapper;

    public SuggestionBoardService(SuggestionBoardMapper suggestionBoardMapper) {
        this.suggestionBoardMapper = suggestionBoardMapper;
    }

    // 전체 게시글 목록 조회
    public List<SuggestionBoard> findAll() {
        return suggestionBoardMapper.findAll();
    }

    // 게시글 상세 조회
    public SuggestionBoard findById(Long postId) {
    	// 조회수 증가 로직
    	suggestionBoardMapper.increaseViews(postId);
    	
        return suggestionBoardMapper.findById(postId);
    }

    // 게시글 등록
    public void insertSuggestion(SuggestionBoard post) {
    	
    	// user ID는 임시로 1로 지정
    	post.setUserId((long) 1);
    	// 최초 생성 시 조회수는 0으로 설정
    	post.setViews((long)0);
    	
    	// 생성일과 업데이트 일은 최초 작성 일자를 넣는다.
    	LocalDateTime now = LocalDateTime.now();
    	post.setCreatedAt(now);
    	post.setUpdatedAt(now);
        suggestionBoardMapper.insertSuggestion(post);
    }

    // 게시글 수정
    public void updateSuggestion(SuggestionBoard post) {
        suggestionBoardMapper.updateSuggestion(post);
    }

    // 게시글 삭제
    public void deleteSuggestion(Long postId) {
        suggestionBoardMapper.deleteSuggestion(postId);
    }
}

