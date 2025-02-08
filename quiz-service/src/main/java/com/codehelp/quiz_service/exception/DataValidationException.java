package com.codehelp.quiz_service.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DataValidationException extends QuizException{
  public DataValidationException(String message) {
    super(message);
  }

  public DataValidationException(String message, String errorDescription) {
    super(message, errorDescription);
  }
}
