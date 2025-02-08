package com.codehelp.quiz_service.core;

import com.codehelp.quiz_service.exception.DataValidationException;
import com.codehelp.quiz_service.exception.NotFoundException;
import com.codehelp.quiz_service.exception.QuizException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionAdvisor {

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorDto> handleException(Exception e) {
    return new ResponseEntity<>(ErrorDto.from(e), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(QuizException.class)
  public ResponseEntity<ErrorDto> handleQuizException(QuizException e) {
    return new ResponseEntity<>(ErrorDto.from(e), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ErrorDto> handleNotFoundException(NotFoundException e) {
    return new ResponseEntity<>(ErrorDto.from(e), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(DataValidationException.class)
  public ResponseEntity<ErrorDto> handleDataValidationException(DataValidationException e) {
    return new ResponseEntity<>(ErrorDto.from(e), HttpStatus.BAD_REQUEST);
  }
}
