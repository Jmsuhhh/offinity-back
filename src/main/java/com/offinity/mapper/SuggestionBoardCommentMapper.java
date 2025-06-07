package com.offinity.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.offinity.dto.SuggestionBoardComment;

@Mapper
public interface SuggestionBoardCommentMapper {

    List<SuggestionBoardComment> getCommentListByPostId(Long postId);

    SuggestionBoardComment getCommentById(Long commentId);
    
    void insertComment(SuggestionBoardComment comment);

    void updateComment(SuggestionBoardComment comment);

    void deleteComment(Long commentId);

}

