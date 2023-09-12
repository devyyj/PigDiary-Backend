package com.devyyj.pigdiary.freeboard.service;

import com.devyyj.pigdiary.common.service.CommonServiceImpl;
import com.devyyj.pigdiary.common.service.CrudService;
import com.devyyj.pigdiary.freeboard.dto.FreeBoardRequestDto;
import com.devyyj.pigdiary.freeboard.dto.FreeBoardResponseDto;
import com.devyyj.pigdiary.common.dto.PageRequestDto;
import com.devyyj.pigdiary.common.dto.PageResultDto;
import com.devyyj.pigdiary.freeboard.entity.FreeBoard;
import com.devyyj.pigdiary.freeboard.repository.FreeBoardRepository;
import com.devyyj.pigdiary.user.entity.MyUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor // 의존성 자동 주입
public class FreeBoardServiceImpl implements CrudService<FreeBoardRequestDto, FreeBoardResponseDto, FreeBoard> {

    private final FreeBoardRepository freeBoardRepository; // 반드시 final로 선언
    private final CommonServiceImpl commonService;

    @Override
    public PageResultDto<FreeBoardResponseDto, FreeBoard> getList(PageRequestDto pageRequestDTO) {
        Pageable pageable = pageRequestDTO.getPageable(Sort.by("id").descending());
        Page<FreeBoard> result = freeBoardRepository.findAll(pageable);
        Function<FreeBoard, FreeBoardResponseDto> fn = (this::entityToDto);
        return new PageResultDto<>(result, fn);
    }

    @Override
    public Long create(FreeBoardRequestDto boardRequestDto, Long userId) {
        FreeBoard entity = dtoToEntity(boardRequestDto);
        entity.setUser(MyUser.builder().id(userId).build());
        freeBoardRepository.save(entity);
        return entity.getId();
    }

    @Override
    public FreeBoardResponseDto read(Long postId) {
        Optional<FreeBoard> result = freeBoardRepository.findById(postId);
        return result.map(this::entityToDto).orElse(null);
    }

    @Override
    public void update(Long userId, FreeBoardRequestDto boardRequestDto) {
        Optional<FreeBoard> post = freeBoardRepository.findById(boardRequestDto.getPostId());
        if (post.isPresent()) {
            FreeBoard freeBoard = post.get();
            commonService.checkDataOwner(userId, freeBoard.getUser().getId());
            freeBoard.setTitle(boardRequestDto.getTitle());
            freeBoard.setContent(boardRequestDto.getContent());
            freeBoardRepository.save(freeBoard);
        }
    }

    @Override
    public void delete(Long userId, Long postId) {
        Optional<FreeBoard> post = freeBoardRepository.findById(postId);
        post.ifPresent(x -> {
            commonService.checkDataOwner(userId, x.getUser().getId());
            x.setDeleted();
            freeBoardRepository.save(x);
        });
    }

    @Override
    public FreeBoard dtoToEntity(FreeBoardRequestDto dto) {
        return FreeBoard
                .builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .build();
    }

    @Override
    public FreeBoardResponseDto entityToDto(FreeBoard entity) {
        return FreeBoardResponseDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .nickName(entity.getUser().getNickName())
                .userId(entity.getUser().getId())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deletedAt(entity.getDeletedAt())
                .deleted(entity.isDeleted())
                .build();
    }
}
