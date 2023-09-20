package com.devyyj.pigdiary.diary.repository;

import com.devyyj.pigdiary.diary.entity.Diary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    Page<Diary> findAllByUserId(Long id, Pageable pageable);
    Optional<Diary> findByIdAndUserId(Long id, Long userId);
}
