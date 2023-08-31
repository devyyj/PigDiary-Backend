package com.devyyj.pigdiary.User;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.devyyj.pigdiary.User.dto.UserRequestDto;
import com.devyyj.pigdiary.User.dto.UserResponseDto;
import com.devyyj.pigdiary.User.entity.MyUser;
import com.devyyj.pigdiary.User.repository.MyUserRepository;
import com.devyyj.pigdiary.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final MyUserRepository userRepository;
    private final JwtUtil jwtUtil;

    @GetMapping("/user")
    public ResponseEntity<UserResponseDto> getUserInfo(Authentication authentication) {
        Optional<MyUser> user = userRepository.findById(Long.valueOf(authentication.getPrincipal().toString()));

        UserResponseDto userResponseDto = new UserResponseDto();
        user.ifPresent(myUser -> {
            userResponseDto.setId(myUser.getId());
            userResponseDto.setNickName(myUser.getNickName());
        });

        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity<Void> setUserInfo(Authentication authentication, @RequestBody UserRequestDto userRequestDto) {
        Optional<MyUser> user = userRepository.findById(Long.valueOf(authentication.getPrincipal().toString()));
        user.ifPresent(myUser -> {
            myUser.setNickName(userRequestDto.getNickName());
            userRepository.save(myUser);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/user")
    public ResponseEntity<Void>  deleteUserInfo(Authentication authentication) {
        Optional<MyUser> user = userRepository.findById(Long.valueOf(authentication.getPrincipal().toString()));
        user.ifPresent(userRepository::delete);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
