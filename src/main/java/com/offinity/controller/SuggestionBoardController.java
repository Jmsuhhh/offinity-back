package com.offinity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.offinity.dto.SuggestionBoard;
import com.offinity.service.SuggestionBoardService;

@CrossOrigin(origins = "http://localhost:8002")
@RestController
@RequestMapping("/api/suggestion")
public class SuggestionBoardController {

	@Autowired
    private SuggestionBoardService suggestionBoardService;

    public SuggestionBoardController(SuggestionBoardService suggestionBoardService) {
        this.suggestionBoardService = suggestionBoardService;
    }

    // 전체 게시글 목록 조회
    @GetMapping
    public List<SuggestionBoard> findAll() {
        return suggestionBoardService.findAll();
    }

    // 게시글 상세 조회
    @GetMapping("/{postId}")
    public SuggestionBoard findById(@PathVariable(name = "postId") Long postId) {

        return suggestionBoardService.findById(postId);
    }

    // 게시글 등록
    @PostMapping
    public void insertSuggestion(@RequestBody SuggestionBoard post) {
        suggestionBoardService.insertSuggestion(post);
    }

    // 게시글 수정
    @PutMapping("/{postId}")
    public void updateSuggestion(@PathVariable(name = "postId") Long postId, @RequestBody SuggestionBoard post) {
        post.setPostId(postId);
        suggestionBoardService.updateSuggestion(post);
    }

    // 게시글 삭제
    @DeleteMapping("/{postId}")
    public void deleteSuggestion(@PathVariable(name = "postId") Long postId) {
        suggestionBoardService.deleteSuggestion(postId);
    }
}
