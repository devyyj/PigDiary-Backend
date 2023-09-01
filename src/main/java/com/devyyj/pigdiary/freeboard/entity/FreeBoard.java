package com.devyyj.pigdiary.freeboard.entity;

import com.devyyj.pigdiary.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

    @Column(length = 50, nullable = false)
    private String user;
}
