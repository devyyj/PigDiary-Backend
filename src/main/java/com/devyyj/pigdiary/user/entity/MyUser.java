package com.devyyj.pigdiary.user.entity;

import com.devyyj.pigdiary.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class MyUser extends BaseEntity {
    @Column(nullable = false, length = 20)
    private String nickName;

    @Column(nullable = false)
    private String socialId;

    @Column(nullable = false)
    private String socialType;
}
