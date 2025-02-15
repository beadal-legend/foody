package com.sparta.baedallegend.domains.user.exception;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum UserErrorCode {
	NOT_EXIST(NOT_FOUND, "요청에 해당하는 사용자가 존재하지 않습니다. : [%s]");

	private final HttpStatus status;
	private final String message;

	UserErrorCode(HttpStatus status, String message) {
		this.status = status;
		this.message = message;
	}
}
