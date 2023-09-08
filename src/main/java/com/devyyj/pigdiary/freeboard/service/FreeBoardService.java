package com.devyyj.pigdiary.freeboard.service;

import com.devyyj.pigdiary.freeboard.dto.FreeBoardRequestDto;
import com.devyyj.pigdiary.freeboard.dto.FreeBoardResponseDto;
import com.devyyj.pigdiary.common.dto.PageRequestDto;
import com.devyyj.pigdiary.common.dto.PageResultDto;
import com.devyyj.pigdiary.freeboard.entity.FreeBoard;

public interface FreeBoardService {
    // 게시글 목록
    PageResultDto<FreeBoardResponseDto, Object[]> getList(PageRequestDto pageRequestDTO);

    // 게시글 생성
    Long createPost(FreeBoardRequestDto boardRequestDto, Long userId);

    // 게시글 내용
    FreeBoardResponseDto getPost(Long postNumber);

    // 게시글 수정
    void update(Long userId, FreeBoardRequestDto boardRequestDto) throws Exception;

    // 게시글 삭제
    void delete(Long userId, Long postNumber);

    default FreeBoard dtoToEntity(FreeBoardRequestDto dto) {
        return FreeBoard
                .builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .build();
    }

    default FreeBoardResponseDto entityToDto(FreeBoard entity) {
        return FreeBoardResponseDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .nickName(entity.getUser().getNickName())
                .userId(entity.getUser().getId())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deletedAt(entity.getDeletedAt())
                .deleted(entity.isDeleted())
                .build();
    }

    default FreeBoardResponseDto entityArrayToDto(Object[] entityArray) {
        FreeBoard freeBoard = (FreeBoard) entityArray[0]; // FreeBoard 엔티티는 배열의 첫 번째 요소
        String nickName = (String) entityArray[1]; // nickName은 배열의 두 번째 요소

        // FreeBoardResponseDto를 생성하고 반환
        return FreeBoardResponseDto.builder()
                .id(freeBoard.getId())
                .title(freeBoard.getTitle())
                .content(freeBoard.getContent())
                .nickName(nickName)
                .userId(freeBoard.getUserId())
                .createdAt(freeBoard.getCreatedAt())
                .updatedAt(freeBoard.getUpdatedAt())
                .deletedAt(freeBoard.getDeletedAt())
                .deleted(freeBoard.isDeleted())
                .build();
    }
}
