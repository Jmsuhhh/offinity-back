package com.offinity.mapper;

import com.offinity.dto.SuggestionBoard;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SuggestionBoardMapper {

    // 전체 게시글 목록 조회
    List<SuggestionBoard> findAll();

    //게시글 조회수 증가
    void increaseViews(Long postId);
    
    // 게시글 상세 조회
    SuggestionBoard findById(Long postId);

    // 게시글 등록
    void insertSuggestion(SuggestionBoard post);

    // 게시글 수정
    void updateSuggestion(SuggestionBoard post);

    // 게시글 삭제
    void deleteSuggestion(Long postId);
}
