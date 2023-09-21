package com.devyyj.pigdiary.freeboard.entity;

import com.devyyj.pigdiary.common.entity.BaseEntity;
import com.devyyj.pigdiary.user.entity.MyUser;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@RequiredArgsConstructor
@SuperBuilder
@AllArgsConstructor
@Where(clause = "deleted = false")
public class FreeBoard extends BaseEntity {
    @Column(length = 50, nullable = false)
    private String title;

    @Column(length = 1500, nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "none"))
    private MyUser user;
}
