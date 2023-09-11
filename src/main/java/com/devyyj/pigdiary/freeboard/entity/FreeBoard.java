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
    @Column(length = 50, nullable = false)
    private String title;

    @Column(length = 1500, nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id"
            , foreignKey = @ForeignKey(name = "none"))
    private MyUser user;
}
