package com.codehelp.question_service.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(force = true)
@EqualsAndHashCode(callSuper = true)
public class QuestionException extends Exception {
  private final String errorDescription;

  public QuestionException(String message) {
    super(message);
    this.errorDescription = "something went wrong";
  }

  public QuestionException(String message, String errorDescription) {
    super(message);
    this.errorDescription = errorDescription;
  }
}
