package com.devyyj.pigdiary.security.dto;

import com.devyyj.pigdiary.User.entity.MyUser;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

@Data
@Builder
public class MyOauth2UserDto implements OAuth2User {
    private String id;
    private String socialId;
    private String nickName;

    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getName() {
        return this.socialId;
    }
}
