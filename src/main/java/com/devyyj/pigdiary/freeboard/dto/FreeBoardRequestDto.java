package com.devyyj.pigdiary.freeboard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FreeBoardRequestDto {
    private Long postId;
    private String title;
    private String content;
}
