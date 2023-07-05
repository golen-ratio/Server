package com.umc.goldenratio.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    DUPLICATE_RESOURCE(CONFLICT, "데이터가 이미 존재합니다");


    private final HttpStatus httpStatus;
    private final String message;
}
