package com.devyyj.pigdiary.diary.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class DiaryResponseDto {
    private String foodName;
    private LocalDate mealDate;
    private String mealTime;
}
