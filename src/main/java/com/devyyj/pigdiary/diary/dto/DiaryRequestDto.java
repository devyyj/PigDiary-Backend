package com.devyyj.pigdiary.diary.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DiaryRequestDto {
    private Long diaryId;
    private String foodName;
    private LocalDate mealDate;
    private String mealTime;
}
