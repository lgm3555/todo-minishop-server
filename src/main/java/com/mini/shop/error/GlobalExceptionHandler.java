package com.mini.shop.error;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.mini.shop.error.exception.DuplicatedUserException;
import com.mini.shop.error.exception.ExpireTokenException;
import com.mini.shop.error.exception.NotFoundUserException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ExpireTokenException.class)
    public ResponseEntity<ErrorResponse> refreshTokenExpiredException() {
        ErrorResponse errorResponse = new ErrorResponse("E01", "Refresh Token이 만료되었습니다. 다시 로그인을 진행하여 Token을 갱신해주세요.");
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({IllegalArgumentException.class, UnsupportedJwtException.class, SecurityException.class, MalformedJwtException.class})
    public ResponseEntity<ErrorResponse> refreshTokenVerificationException() {
        ErrorResponse errorResponse = new ErrorResponse("E02", "유효하지 않은 Refresh Token 입니다.");
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NotFoundUserException.class)
    protected ResponseEntity<Object> notFoundUserException() {
        ErrorResponse errorResponse = new ErrorResponse("E03", "사용자 정보를 찾을 수 없습니다.");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicatedUserException.class)
    protected ResponseEntity<Object> handleNotFoundException(Exception ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse("E04", "이미 존재하는 아이디 입니다.");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(JWTVerificationException.class)
    protected ResponseEntity<Object> handleVerificationException(Exception ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse("E05", "JWT Token이 존재하지 않습니다.");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
