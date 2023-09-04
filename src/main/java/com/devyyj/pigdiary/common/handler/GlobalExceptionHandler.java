package com.devyyj.pigdiary.common.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException(Exception e) {
        // 예외 처리 로직을 여기에 작성합니다.
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // 다른 예외 핸들러 메서드를 추가할 수 있습니다.
    // 예를 들어, 특정 예외에 대한 핸들러 메서드를 추가할 수 있습니다.

//    @ExceptionHandler(CustomException.class)
//    public ResponseEntity<String> handleCustomException(CustomException e) {
//        // 특정 예외에 대한 처리 로직을 여기에 작성합니다.
//        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//    }
}