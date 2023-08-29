package com.devyyj.pigdiary.common.repository;

import com.devyyj.pigdiary.common.entity.CommonCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CommonCodeRepository extends JpaRepository<CommonCode, Long> {
    // category 값을 기준으로 임의의 1개 행 가져오기
    @Query(value = "SELECT * FROM common_code WHERE category = ?1 ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Optional<CommonCode> findRandomByCategory(String category);

}
