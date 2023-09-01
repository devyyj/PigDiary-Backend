package com.devyyj.pigdiary.freeboard.repository;

import com.devyyj.pigdiary.freeboard.entity.FreeBoard;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface FreeBoardRepository extends JpaRepository<FreeBoard, Long>/*, QuerydslPredicateExecutor<FreeBoard>*/ {
}
