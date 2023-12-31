package com.devyyj.pigdiary.common.service;

import com.devyyj.pigdiary.common.dto.PageRequestDto;
import com.devyyj.pigdiary.common.dto.PageResponseDto;

public interface CrudService<RequestDto, ResponseDto, Entity> {
    // 목록
    PageResponseDto<ResponseDto, Entity> getList(Long userId, PageRequestDto pageRequestDTO);
    PageResponseDto<ResponseDto, Entity> getList(PageRequestDto pageRequestDTO);

    // 생성
    Long create(Long userId, RequestDto requestDto);

    // 내용
    ResponseDto read(Long userId, Long postId);
    ResponseDto read(Long postId);

    // 수정
    void update(Long userId, RequestDto requestDto);

    // 삭제
    void delete(Long userId, Long postId);

    Entity dtoToEntity(RequestDto dto);

    ResponseDto entityToDto(Entity entity);
}
