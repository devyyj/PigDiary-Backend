package com.devyyj.pigdiary.diary.entity;

import com.devyyj.pigdiary.common.entity.BaseEntity;
import com.devyyj.pigdiary.common.entity.CommonCode;
import com.devyyj.pigdiary.user.entity.MyUser;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;

import java.time.LocalDate;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@RequiredArgsConstructor
@Where(clause = "deleted = false")
public class Diary extends BaseEntity {
    @Column(nullable = false, length = 20)
    private String foodName;

    @Column(nullable = false)
    private LocalDate mealDate;

    @Column(nullable = false, name = "meal_time")
    private String mealTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meal_time"
            , referencedColumnName = "name"
            , nullable = false
            , insertable=false, updatable=false
            , foreignKey = @ForeignKey(name = "none"))
    @Where(clause = "category = '식사시간'")
    private CommonCode commonCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "none"))
    private MyUser user;
}
