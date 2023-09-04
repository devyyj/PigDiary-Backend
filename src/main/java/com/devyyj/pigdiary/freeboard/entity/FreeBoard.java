package com.devyyj.pigdiary.freeboard.entity;

import com.devyyj.pigdiary.common.entity.BaseEntity;
import com.devyyj.pigdiary.user.entity.MyUser;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@RequiredArgsConstructor
@SuperBuilder
@AllArgsConstructor
public class FreeBoard extends BaseEntity {
    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 1500, nullable = false)
    private String content;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private MyUser user;
}
