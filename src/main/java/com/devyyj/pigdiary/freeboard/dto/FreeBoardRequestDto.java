package com.devyyj.pigdiary.freeboard.dto;

import com.devyyj.pigdiary.common.dto.BaseDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * DTO(Data Transfer Object)는 엔티티 객체와 달리 각 계층끼리 주고받는 우편물이나 상자의 개념
 * 순수하게 데이터를 담고 있다는 점에서는 엔티티 객체와 유사하지만,
 * 목적 자체가 데이터의 전달이므로 읽고, 쓰는 것이 모두 허용된다.
 * 일회성으로 사용되는 성격이 강하다.
 * <p>
 * 웹 애플리케이션을 제작할 때는 HttpServletRequest, HttpServletResponse를 서비스 계층으로 전달하지 않는 것을 원칙으로 한다.
 * 유사하게 엔티티 객체가 JPA에서 사용하는 객체이므로 JPA 외에서 사용하지 않는 것을 권장한다.
 * <p>
 * DTO를 사용하는 경우 가장 큰 단점은 엔티티와 유사한 코드를 중복으로 개발한다는 점과
 * 엔티티 객체를 DTO로 변환하거나 그 반대로 변환하는 과정이 필요하는 점이다.
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FreeBoardRequestDto {
    private String title;
    private String content;
}
