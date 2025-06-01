package com.offinity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.offinity.dto.SuggestionBoardComment;
import com.offinity.mapper.SuggestionBoardCommentMapper;

@Service
public class SuggestionBoardCommentService {

    @Autowired
    private SuggestionBoardCommentMapper suggestionBoardCommentMapper;

    // 게시글에 해당하는 전체 댓글 조회
    public List<SuggestionBoardComment> getCommentListByPostId(Long postId) {
        return suggestionBoardCommentMapper.getCommentListByPostId(postId);
    }
    
    // 단일 댓글 조회
    public SuggestionBoardComment getCommentById(Long commentId) {
    	return suggestionBoardCommentMapper.getCommentById(commentId);
    }
    
    // 댓글 추가
    public void insertComment(SuggestionBoardComment comment) {
    	suggestionBoardCommentMapper.insertComment(comment);
    }

    // 댓글 수정
    public void updateComment(SuggestionBoardComment comment) {
    	suggestionBoardCommentMapper.updateComment(comment);
    }

    // 댓글 삭제
    public void deleteComment(Long commentId) {
    	suggestionBoardCommentMapper.deleteComment(commentId);
    }

}
