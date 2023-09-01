package com.devyyj.pigdiary.freeboard.service;

import com.devyyj.pigdiary.freeboard.dto.FreeBoardDto;
import com.devyyj.pigdiary.freeboard.dto.PageRequestDto;
import com.devyyj.pigdiary.freeboard.dto.PageResultDto;
import com.devyyj.pigdiary.freeboard.entity.FreeBoard;

public interface FreeBoardService {
    // 게시글 목록
    PageResultDto<FreeBoardDto, FreeBoard> getList(PageRequestDto pageRequestDTO);

    // 게시글 생성
    Long createPost(FreeBoardDto dto);

    // 게시글 내용
    FreeBoardDto getPost(Long postNumber);

    // 게시글 수정
    void update(Long postNumber, FreeBoardDto freeBoardDTO);

    // 게시글 삭제
    void delete(Long postNumber);

    default FreeBoard dtoToEntity(FreeBoardDto dto) {
        return FreeBoard
                .builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .content(dto.getContent())
                .user(dto.getUser())
                .build();
    }

    default FreeBoardDto entityToDto(FreeBoard entity) {
        return FreeBoardDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .user(entity.getUser())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deletedAt(entity.getDeletedAt())
                .deleted(entity.isDeleted())
                .build();
    }


}
