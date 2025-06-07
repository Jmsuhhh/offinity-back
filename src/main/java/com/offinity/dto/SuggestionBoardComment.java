package com.offinity.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuggestionBoardComment {

    private Long commentId;           // 댓글 ID
    private Long postId;              // 건의글 ID (FK)
    private String content;          // 댓글 내용
    private LocalDateTime createdAt; // 작성일자
    private LocalDateTime updatedAt; // 수정일자
}
