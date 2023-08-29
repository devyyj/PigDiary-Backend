package com.devyyj.pigdiary.User.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nickName;
    @Column(nullable = false)
    private String socialId;
    @Column(nullable = false)
    private String socialType;
}
