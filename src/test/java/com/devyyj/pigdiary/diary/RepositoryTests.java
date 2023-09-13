package com.devyyj.pigdiary.diary;

import com.devyyj.pigdiary.common.dto.PageRequestDto;
import com.devyyj.pigdiary.common.dto.PageResponseDto;
import com.devyyj.pigdiary.diary.dto.DiaryRequestDto;
import com.devyyj.pigdiary.diary.dto.DiaryResponseDto;
import com.devyyj.pigdiary.diary.entity.Diary;
import com.devyyj.pigdiary.diary.service.DiaryServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Random;

@SpringBootTest
public class RepositoryTests {
    @Autowired
    private DiaryServiceImpl diaryService;

    @Test
    void create() {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            // 1 이상 5 이하의 임의의 정수 생성
            int randomNumber = random.nextInt(5) + 1;
            DiaryRequestDto dto = new DiaryRequestDto();
            dto.setFoodName("김치찌개" + i);
            dto.setMealDate(LocalDate.of(2023, 9, 12));
            dto.setMealTime(String.valueOf(randomNumber));
            diaryService.create(dto, 1L);
        }
    }

    @Test
    void getList() {
        PageRequestDto pageRequestDto = new PageRequestDto();
        PageResponseDto<DiaryResponseDto, Diary> pageResponseDto = diaryService.getList(pageRequestDto);
        System.out.println("getList() ======================================");
        System.out.println(pageResponseDto.toString());
        System.out.println("======================================");
    }

    @Test
    void read() {
        DiaryResponseDto dto = diaryService.read(1L);
        System.out.println("read() =========================================");
        System.out.println(dto.toString());
        System.out.println("=========================================");
    }
}
