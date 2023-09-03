package com.devyyj.pigdiary.freeboard.service;

import com.devyyj.pigdiary.freeboard.dto.FreeBoardRequestDto;
import com.devyyj.pigdiary.freeboard.dto.FreeBoardResponseDto;
import com.devyyj.pigdiary.freeboard.dto.PageRequestDto;
import com.devyyj.pigdiary.freeboard.dto.PageResultDto;
import com.devyyj.pigdiary.freeboard.entity.FreeBoard;

public interface FreeBoardService {
    // 게시글 목록
    PageResultDto<FreeBoardResponseDto, FreeBoard> getList(PageRequestDto pageRequestDTO);

    // 게시글 생성
    Long createPost(FreeBoardRequestDto boardRequestDto, Long userId);

    // 게시글 내용
    FreeBoardResponseDto getPost(Long postNumber);

    // 게시글 수정
    void update(Long postNumber, FreeBoardRequestDto boardRequestDto);

    // 게시글 삭제
    void delete(Long postNumber);

    default FreeBoard dtoToEntity(FreeBoardRequestDto dto) {
        return FreeBoard
                .builder()
                .userId()
                .title(dto.getTitle())
                .content(dto.getContent())
                .build();
    }

    default FreeBoardResponseDto entityToDto(FreeBoard entity) {
        return FreeBoardResponseDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .nickName(entity.getNickName())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deletedAt(entity.getDeletedAt())
                .deleted(entity.isDeleted())
                .build();
    }


}
