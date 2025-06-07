package com.offinity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.offinity.dto.SuggestionBoardComment;
import com.offinity.service.SuggestionBoardCommentService;

@CrossOrigin(origins = "http://localhost:8002")
@RestController
@RequestMapping("/api/suggestion/comments")
public class SuggestionBoardCommentController {

    @Autowired
    private SuggestionBoardCommentService commentService;

    // 특정 건의글(postId)에 대한 댓글 전체 조회
    @GetMapping("/{postId}")
    public List<SuggestionBoardComment> getCommentsByPostId(@PathVariable(name = "postId") Long postId) {
        return commentService.getCommentListByPostId(postId);
    }

    // 댓글 등록
    @PostMapping
    public void insertComment(@RequestBody SuggestionBoardComment comment) {
        commentService.insertComment(comment);
    }

    // 댓글 수정
    @PutMapping("/{commentId}")
    public void updateComment(@PathVariable(name = "commentId") Long commentId, @RequestBody SuggestionBoardComment comment) {
        comment.setCommentId(commentId);
        commentService.updateComment(comment);
    }

    // 댓글 삭제
    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable(name = "commentId") Long commentId) {
        commentService.deleteComment(commentId);
    }
}
