package com.devyyj.pigdiary.freeboard.repository;

import com.devyyj.pigdiary.freeboard.entity.FreeBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FreeBoardRepository extends JpaRepository<FreeBoard, Long> {
}
