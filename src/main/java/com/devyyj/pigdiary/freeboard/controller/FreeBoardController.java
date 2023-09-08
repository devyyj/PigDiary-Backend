package com.devyyj.pigdiary.freeboard.controller;

import com.devyyj.pigdiary.freeboard.dto.FreeBoardRequestDto;
import com.devyyj.pigdiary.freeboard.dto.FreeBoardResponseDto;
import com.devyyj.pigdiary.common.dto.PageRequestDto;
import com.devyyj.pigdiary.common.dto.PageResultDto;
import com.devyyj.pigdiary.freeboard.service.FreeBoardServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/freeboard")
@Log4j2
@RequiredArgsConstructor
public class FreeBoardController {
    private final FreeBoardServiceImpl freeBoardService;
    // 게시글 목록 조회
    @GetMapping({"", "/"})
    public ResponseEntity<PageResultDto> list(PageRequestDto pageRequestDTO) {
        return new ResponseEntity<>(freeBoardService.getList(pageRequestDTO), HttpStatus.OK);
    }
    // 게시글 조회
    @GetMapping("/{postNumber}")
    public ResponseEntity<FreeBoardResponseDto> readPost(@PathVariable Long postNumber) {
        return new ResponseEntity<>(freeBoardService.getPost(postNumber), HttpStatus.OK);
    }
    // 게시글 생성
    @PostMapping({"", "/"})
    public ResponseEntity<Long> createPost(Authentication authentication, FreeBoardRequestDto boardRequestDto) {
        return new ResponseEntity<>(freeBoardService.createPost(boardRequestDto, Long.valueOf(authentication.getPrincipal().toString())), HttpStatus.OK);
    }
    // 게시글 수정
    @PutMapping("/{postNumber}")
    public ResponseEntity<String> updatePost(Authentication authentication, FreeBoardRequestDto boardRequestDto) throws Exception {
        freeBoardService.update(Long.valueOf(authentication.getPrincipal().toString()), boardRequestDto);
        return new ResponseEntity<>(boardRequestDto.getPostId() + " post updated.", HttpStatus.OK);
    }
    // 게시글 삭제
    @DeleteMapping("/{postNumber}")
    public ResponseEntity<String> deletePost(Authentication authentication, @PathVariable Long postNumber) {
        freeBoardService.delete(Long.valueOf(authentication.getPrincipal().toString()), postNumber);
        return new ResponseEntity<>(postNumber + " post deleted.", HttpStatus.OK);
    }
}
