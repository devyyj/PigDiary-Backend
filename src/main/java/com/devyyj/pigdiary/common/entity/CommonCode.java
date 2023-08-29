package com.devyyj.pigdiary.common.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CommonCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;
}
