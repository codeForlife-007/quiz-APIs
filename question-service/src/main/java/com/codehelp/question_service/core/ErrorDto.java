package com.codehelp.question_service.core;

import com.codehelp.question_service.exception.QuestionException;
import lombok.Data;

@Data
public class ErrorDto {
  private String message;
  private String errorDescription;

  public static ErrorDto from(Exception e) {
    ErrorDto errorDto = new ErrorDto();
    errorDto.setMessage(e.getMessage());
    Throwable cause = e.getCause();
    if (cause != null) {
      errorDto.setErrorDescription(cause.getLocalizedMessage());
    }
    return errorDto;
  }

  public static ErrorDto from(QuestionException e) {
    ErrorDto errorDto = new ErrorDto();
    errorDto.setMessage(e.getMessage());
    errorDto.setErrorDescription(e.getLocalizedMessage());
    return errorDto;
  }
}
