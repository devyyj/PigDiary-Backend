package com.devyyj.pigdiary.diary.repository;

import com.devyyj.pigdiary.diary.entity.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
}
