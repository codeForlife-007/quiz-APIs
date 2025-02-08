package com.codehelp.question_service.dto;

import com.codehelp.question_service.model.Question;
import lombok.Data;

@Data
public class QuestionDto {
  private String questionTitle;
  private String option1;
  private String option2;
  private String option3;
  private String option4;
  private String rightAnswer;
  private String difficultyLevel;
  private String category;

  // method to convert Dto to entity
  public Question toEntity() {
    Question question = new Question();
    question.setQuestionTitle(getQuestionTitle());
    question.setOption1(getOption1());
    question.setOption2(getOption2());
    question.setOption3(getOption3());
    question.setOption4(getOption4());
    question.setRightAnswer(getRightAnswer());
    question.setCategory(getCategory());
    return question;
  }
}
