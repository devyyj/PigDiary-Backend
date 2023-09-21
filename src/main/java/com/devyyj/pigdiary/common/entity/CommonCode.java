package com.devyyj.pigdiary.common.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@SuperBuilder
@RequiredArgsConstructor
@Where(clause = "deleted = false")
public class CommonCode extends BaseEntity{
    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;
}
