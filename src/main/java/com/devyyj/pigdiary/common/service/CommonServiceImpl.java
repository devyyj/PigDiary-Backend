package com.devyyj.pigdiary.common.service;

import com.devyyj.pigdiary.common.exception.UnauthorizedException;
import com.devyyj.pigdiary.freeboard.entity.FreeBoard;
import com.devyyj.pigdiary.freeboard.repository.FreeBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommonServiceImpl implements CommonService {

    @Override
    public void checkDataOwner(Long userId, Long checkId) {
        if (!userId.equals(checkId)) throw new UnauthorizedException("Who are you?");
    }
}
