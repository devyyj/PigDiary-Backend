package com.devyyj.pigdiary.diary;

import com.devyyj.pigdiary.diary.entity.Diary;
import com.devyyj.pigdiary.diary.repository.DiaryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;

import java.time.LocalDate;

@SpringBootTest
public class RepositoryTests {
    @Autowired
    private DiaryRepository diaryRepository;

    @Test
    void test() {
        diaryRepository.save(
                Diary.builder()
                        .userId(10L)
                        .foodName("삼겹살")
                        .mealDate(LocalDate.of(2023, 9, 6))
                        .build()
        );
    }
}
