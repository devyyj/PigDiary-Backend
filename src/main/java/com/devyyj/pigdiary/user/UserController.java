package com.devyyj.pigdiary.user;

import com.devyyj.pigdiary.login.service.LoginServiceImpl;
import com.devyyj.pigdiary.user.dto.UserRequestDto;
import com.devyyj.pigdiary.user.dto.UserResponseDto;
import com.devyyj.pigdiary.user.entity.MyUser;
import com.devyyj.pigdiary.user.repository.MyUserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final MyUserRepository userRepository;
    private final LoginServiceImpl loginService;

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

    @PutMapping("/user")
    public ResponseEntity<Void> setUserInfo(Authentication authentication, @RequestBody UserRequestDto userRequestDto) {
        Optional<MyUser> user = userRepository.findById(Long.valueOf(authentication.getPrincipal().toString()));
        user.ifPresent(myUser -> {
            myUser.setNickName(userRequestDto.getNickName());
            userRepository.save(myUser);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/user")
    public ResponseEntity<Void> deleteUserInfo(Authentication authentication, HttpServletResponse response) {
        Optional<MyUser> user = userRepository.findById(Long.valueOf(authentication.getPrincipal().toString()));
        user.ifPresent(x -> {
            x.markAsDeleted();
            userRepository.save(x);
            loginService.clearCookie(response);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
