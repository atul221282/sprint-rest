package io.javabrains.sbs.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@ControllerAdvice
public class ErrorHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionResponse> handleGeneralException(Exception ex) throws Exception {
		ExceptionResponse er = new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
		System.out.println(er);
		return new ResponseEntity<ExceptionResponse>(er, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

@Data
@RequiredArgsConstructor
class ExceptionResponse {
	@NonNull
	private Integer code;
	@NonNull
	private String descritpion;
}
