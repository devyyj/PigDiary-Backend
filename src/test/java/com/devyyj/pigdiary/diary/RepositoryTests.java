package com.devyyj.pigdiary.diary;

import com.devyyj.pigdiary.common.dto.PageRequestDto;
import com.devyyj.pigdiary.common.dto.PageResultDto;
import com.devyyj.pigdiary.diary.dto.DiaryRequestDto;
import com.devyyj.pigdiary.diary.dto.DiaryResponseDto;
import com.devyyj.pigdiary.diary.entity.Diary;
import com.devyyj.pigdiary.diary.service.DiaryServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class RepositoryTests {
    @Autowired
    private DiaryServiceImpl diaryService;

    @Test
    void create() {
        DiaryRequestDto dto = new DiaryRequestDto();
        dto.setFoodName("김치찌개");
        dto.setMealDate(LocalDate.of(2023, 9 ,12));
        dto.setMealTime("2");
        diaryService.create(dto, 1L);
    }

    @Test
    void read(){
         PageResultDto<DiaryResponseDto, Diary> pageResultDto = diaryService.getList(PageRequestDto.builder().build());
         System.out.println(pageResultDto.toString());
    }
}
