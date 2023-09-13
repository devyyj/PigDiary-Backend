package com.devyyj.pigdiary.diary.service;

import com.devyyj.pigdiary.common.dto.PageRequestDto;
import com.devyyj.pigdiary.common.dto.PageResponseDto;
import com.devyyj.pigdiary.common.service.CrudService;
import com.devyyj.pigdiary.diary.dto.DiaryRequestDto;
import com.devyyj.pigdiary.diary.dto.DiaryResponseDto;
import com.devyyj.pigdiary.diary.entity.Diary;
import com.devyyj.pigdiary.diary.repository.DiaryRepository;
import com.devyyj.pigdiary.user.entity.MyUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class DiaryServiceImpl implements CrudService<DiaryRequestDto, DiaryResponseDto, Diary> {

    private final DiaryRepository diaryRepository;

    //todo 동작전 사용자 검증 로직 추가

    @Override
    public PageResponseDto<DiaryResponseDto, Diary> getList(PageRequestDto pageRequestDTO) {
        Pageable pageable = pageRequestDTO.getPageable(Sort.by("id").descending());
        Page<Diary> result = diaryRepository.findAll(pageable);
        Function<Diary, DiaryResponseDto> fn = (this::entityToDto);
        return new PageResponseDto<>(result, fn);
    }

    @Override
    public Long create(DiaryRequestDto diaryRequestDto, Long userId) {
        Diary entity = dtoToEntity(diaryRequestDto);
        entity.setUser(MyUser.builder().id(userId).build());
        diaryRepository.save(entity);
        return entity.getId();
    }

    @Override
    public DiaryResponseDto read(Long postId) {
        Optional<Diary> diary = diaryRepository.findById(postId);
        return diary.map(this::entityToDto).orElseThrow();
    }

    @Override
    public void update(Long userId, DiaryRequestDto diaryRequestDto) {
        Optional<Diary> diary = diaryRepository.findById(diaryRequestDto.getDiaryId());
        diary.ifPresent(x -> {
            x.setFoodName(diaryRequestDto.getFoodName());
            x.setMealDate(diaryRequestDto.getMealDate());
            x.setMealTime(diaryRequestDto.getMealTime());
            diaryRepository.save(x);
        });
    }

    @Override
    public void delete(Long userId, Long postId) {
        Optional<Diary> diary = diaryRepository.findById(postId);
        diary.ifPresent(x -> {
            x.setDeleted();
            diaryRepository.save(x);
        });
    }

    @Override
    public Diary dtoToEntity(DiaryRequestDto diaryRequestDto) {
        return Diary.builder()
                .foodName(diaryRequestDto.getFoodName())
                .mealDate(diaryRequestDto.getMealDate())
                .mealTime(diaryRequestDto.getMealTime())
                .build();
    }

    @Override
    public DiaryResponseDto entityToDto(Diary diary) {
        return DiaryResponseDto.builder()
                .foodName(diary.getFoodName())
                .mealDate(diary.getMealDate())
                .mealTime(diary.getCommonCode().getDescription())
                .build();
    }
}
