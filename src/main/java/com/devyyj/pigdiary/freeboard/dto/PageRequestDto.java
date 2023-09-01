package com.devyyj.pigdiary.freeboard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * 목록 페이지를 요청할 때 사용하는 데이터를 재사용하기 쉽게 만드는 클래스
 * 화면에서 전달되는 목록 관련된 데이터에 대한 DTO (프론트 -> 백)
 */
@Builder
@AllArgsConstructor
@Data
public class PageRequestDto {
    private int page;
    private int size;

    public PageRequestDto() {
        this.page = 1;
        this.size = 10;
    }

    /**
     * JPA에서 사용하는 Pageable 객체를 반환
     */
    public Pageable getPageable(Sort sort) {
        // JPA를 사용하는 경우에는 페이지 번호가 0부터 시작한다는 점을 감안해서 page - 1
        return PageRequest.of(page - 1, size, sort);
    }
}
