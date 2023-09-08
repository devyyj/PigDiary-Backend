package com.devyyj.pigdiary.diary.entity;

import com.devyyj.pigdiary.common.entity.BaseEntity;
import com.devyyj.pigdiary.common.entity.CommonCode;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@RequiredArgsConstructor
public class Diary extends BaseEntity {
    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String foodName;

    @Column(nullable = false)
    private LocalDate mealDate;

    @Column(nullable = false)
    private String mealTime;

    @ManyToOne(fetch = FetchType.LAZY)
    private CommonCode commonCode;
}
