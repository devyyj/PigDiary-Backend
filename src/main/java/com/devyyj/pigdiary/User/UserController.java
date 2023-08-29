package com.devyyj.pigdiary.User;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.devyyj.pigdiary.User.dto.UserResponseDto;
import com.devyyj.pigdiary.User.entity.MyUser;
import com.devyyj.pigdiary.User.repository.MyUserRepository;
import com.devyyj.pigdiary.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final MyUserRepository userRepository;
    private final JwtUtil jwtUtil;

    @GetMapping("/user")
    public ResponseEntity<UserResponseDto> getUserInfo(@CookieValue String jwt){
        DecodedJWT decodedJWT = jwtUtil.verifyToken(jwt);
        Optional<MyUser> user = userRepository.findById(Long.valueOf(decodedJWT.getSubject()));

        UserResponseDto userResponseDto = new UserResponseDto();
        user.ifPresent(myUser -> userResponseDto.setNickName(myUser.getNickName()));

        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }
}
