package com.devyyj.pigdiary.freeboard.dto;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 화면에서 필요한 데이터
 */
@Data
public class PageResultDto<DTO, EN> {
    private List<DTO> dtoList;

    // 총 페이지 번호
    private int totalPage;
    // 현재 페이지 번호
    private int page;

    // 목록 사이즈
    private int size;
    // 이전, 다음
    private int start, end;

    private boolean prev, next;

    // 페이지 번호 목록
    private List<Integer> pageList;

    /**
     * Page<Entity> result를 DTO 리스트로 변환
     */
    public PageResultDto(Page<EN> result, Function<EN, DTO> fn) {
        // 이거 좀 어려운데?? 이해가 잘 안된다.
        dtoList = result.stream().map(fn).collect(Collectors.toList());

        totalPage = result.getTotalPages();

        makePageList(result.getPageable());
    }

    private void makePageList(Pageable pageable) {
        page = pageable.getPageNumber() + 1; // 0부터 시작하기 때문에 1 추가
        size = pageable.getPageSize();

        int tempEnd = (int) (Math.ceil((double) page / 5)) * 5;
        start = tempEnd - 4;
        prev = start > 1;
        end = Math.min(totalPage, tempEnd);
        next = totalPage > tempEnd;

        pageList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
    }
}
