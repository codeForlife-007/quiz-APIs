package com.codehelp.quiz_service.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(force = true)
@EqualsAndHashCode(callSuper = true)
public class QuizException extends Exception {
  private final String errorDescription;

  public QuizException(String message) {
    super(message);
    this.errorDescription = "something went wrong";
  }

  public QuizException(String message, String errorDescription) {
    super(message);
    this.errorDescription = errorDescription;
  }
}
