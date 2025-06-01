package com.offinity.service;

import com.offinity.dto.SuggestionBoard;
import com.offinity.mapper.SuggestionBoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return suggestionBoardMapper.findById(postId);
    }

    // 게시글 등록
    public void insertSuggestion(SuggestionBoard post) {
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

