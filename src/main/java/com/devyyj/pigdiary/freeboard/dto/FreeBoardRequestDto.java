package com.devyyj.pigdiary.freeboard.dto;

import lombok.*;

@Getter
public class FreeBoardRequestDto {
    private Long postId;
    private String title;
    private String content;
}
