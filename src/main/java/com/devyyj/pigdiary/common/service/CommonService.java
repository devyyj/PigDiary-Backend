package com.devyyj.pigdiary.common.service;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommonService {
    public void checkDataOwner(Long userId, Long checkId);
}
