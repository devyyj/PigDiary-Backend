package com.devyyj.pigdiary.freeboard.service;

import com.devyyj.pigdiary.freeboard.dto.FreeBoardDto;
import com.devyyj.pigdiary.freeboard.dto.PageRequestDto;
import com.devyyj.pigdiary.freeboard.dto.PageResultDto;
import com.devyyj.pigdiary.freeboard.entity.FreeBoard;
import com.devyyj.pigdiary.freeboard.repository.FreeBoardRepository;
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
public class FreeBoardServiceImpl implements FreeBoardService {

    private final FreeBoardRepository freeBoardRepository; // 반드시 final로 선언

    @Override
    public Long createPost(FreeBoardDto dto) {
        log.info("DTO------------------------");
        log.info(dto);
        FreeBoard entity = dtoToEntity(dto);
        log.info(entity);
        freeBoardRepository.save(entity);
        return entity.getId();
    }

    @Override
    public PageResultDto<FreeBoardDto, FreeBoard> getList(PageRequestDto pageRequestDTO) {
        Pageable pageable = pageRequestDTO.getPageable(Sort.by("id").descending());
        Page<FreeBoard> result = freeBoardRepository.findAll(pageable);
//        Function<FreeBoard, FreeBoardDTO> fn = (entity -> entityToDto(entity));
        Function<FreeBoard, FreeBoardDto> fn = (this::entityToDto);
        return new PageResultDto<>(result, fn);
    }

    @Override
    public FreeBoardDto getPost(Long postNumber) {
        Optional<FreeBoard> result = freeBoardRepository.findById(postNumber);
        // isPresent() 공부하기
//        return result.isPresent() ? entityToDto(result.get()) : null;
        return result.map(this::entityToDto).orElse(null);
    }

    @Override
    public void update(Long postNumber, FreeBoardDto freeBoardDTO) {
        Optional<FreeBoard> result = freeBoardRepository.findById(postNumber);

        if (result.isPresent()) {
            FreeBoard freeBoard = result.get();

            freeBoard.setUser(freeBoardDTO.getUser());
            freeBoard.setTitle(freeBoardDTO.getTitle());
            freeBoard.setContent(freeBoardDTO.getContent());

            freeBoardRepository.save(freeBoard);
        }
    }

    @Override
    public void delete(Long postNumber) {
        freeBoardRepository.deleteById(postNumber);
    }
}
