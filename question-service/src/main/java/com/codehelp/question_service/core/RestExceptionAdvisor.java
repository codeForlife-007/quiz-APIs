package com.codehelp.question_service.core;

import com.codehelp.question_service.exception.DataValidationException;
import com.codehelp.question_service.exception.NotFoundException;
import com.codehelp.question_service.exception.QuestionException;
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

  @ExceptionHandler(QuestionException.class)
  public ResponseEntity<ErrorDto> handleQuestionException(QuestionException e) {
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
