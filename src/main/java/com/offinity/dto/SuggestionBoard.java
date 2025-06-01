package com.offinity.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SuggestionBoard {
    private Long postId;     // 게시글 ID
    private String title;          // 게시글 제목
    private String content;        // 게시글 내용
    private Long views;         // 조회수
    private String visible;        // 게시글 공개 여부
    private LocalDateTime createdAt; // 게시글 작성 일자
    private LocalDateTime updatedAt; // 게시글 수정 일자
    private Long userId;           // 사용자 고유 ID
}