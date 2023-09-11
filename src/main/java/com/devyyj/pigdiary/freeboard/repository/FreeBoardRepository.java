package com.devyyj.pigdiary.freeboard.repository;

import com.devyyj.pigdiary.freeboard.entity.FreeBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FreeBoardRepository extends JpaRepository<FreeBoard, Long> {

    @Query("SELECT f, u.nickName FROM FreeBoard f LEFT JOIN f.user u  ON f.user.id = u.id WHERE f.deleted = false")
    Page<FreeBoard> findAllData(Pageable pageable);
}
