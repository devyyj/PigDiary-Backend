package com.devyyj.pigdiary.diary;

import com.devyyj.pigdiary.common.dto.PageRequestDto;
import com.devyyj.pigdiary.common.dto.PageResponseDto;
import com.devyyj.pigdiary.diary.dto.DiaryRequestDto;
import com.devyyj.pigdiary.diary.dto.DiaryResponseDto;
import com.devyyj.pigdiary.diary.service.DiaryServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/diary")
@Log4j2
@RequiredArgsConstructor
public class DiaryController {
    private final DiaryServiceImpl diaryService;
    // 목록 조회
    @GetMapping({"", "/"})
    public ResponseEntity<PageResponseDto> list(Authentication authentication, PageRequestDto pageRequestDTO) {
        return new ResponseEntity<>(diaryService.getList(Long.valueOf(authentication.getPrincipal().toString()), pageRequestDTO), HttpStatus.OK);
    }
    // 조회
    @GetMapping("/{postNumber}")
    public ResponseEntity<DiaryResponseDto> readPost(Authentication authentication, @PathVariable Long postNumber) {
        return new ResponseEntity<>(diaryService.read(Long.valueOf(authentication.getPrincipal().toString()),postNumber), HttpStatus.OK);
    }
    // 생성
    @PostMapping({"", "/"})
    public ResponseEntity<Long> createPost(Authentication authentication, @RequestBody DiaryRequestDto diaryRequestDto) {
        return new ResponseEntity<>(diaryService.create(Long.valueOf(authentication.getPrincipal().toString()), diaryRequestDto), HttpStatus.OK);
    }
    // 수정
    @PutMapping("/{postNumber}")
    public ResponseEntity<String> updatePost(Authentication authentication, @RequestBody DiaryRequestDto diaryRequestDto) throws Exception {
        diaryService.update(Long.valueOf(authentication.getPrincipal().toString()), diaryRequestDto);
        return new ResponseEntity<>(diaryRequestDto.getDiaryId() + " post updated.", HttpStatus.OK);
    }
    // 삭제
    @DeleteMapping("/{postNumber}")
    public ResponseEntity<String> deletePost(Authentication authentication, @PathVariable Long postNumber) {
        diaryService.delete(Long.valueOf(authentication.getPrincipal().toString()), postNumber);
        return new ResponseEntity<>(postNumber + " post deleted.", HttpStatus.OK);
    }
}