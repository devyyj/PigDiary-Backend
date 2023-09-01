package com.devyyj.pigdiary.common.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class CommonCode extends BaseEntity{
    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;
}
