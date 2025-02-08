package com.codehelp.quiz_service.service;

import com.codehelp.quiz_service.dao.QuizDao;
import com.codehelp.quiz_service.exception.NotFoundException;
import com.codehelp.quiz_service.feign.QuizInterface;
import com.codehelp.quiz_service.model.QuestionWrapper;
import com.codehelp.quiz_service.model.Quiz;
import com.codehelp.quiz_service.model.UserResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class QuizService {

  private static final String QUIZ_NOT_FOUND = "quiz not found";
  private QuizDao quizDao;
  private QuizInterface quizInterface;

  public Quiz createQuiz(String category, int noOfQue, String title) {
    // Should check noOfQue count is > 0 and < than questions.length
    List<Integer> questionsIds = quizInterface.getQuestionsForQuiz(category, noOfQue).getBody();
    Quiz quiz = new Quiz();
    quiz.setTitle(title);
    quiz.setQuestionsIds(questionsIds);
    return quizDao.save(quiz);
  }

  public List<QuestionWrapper> getQuizQuestions(Integer id) throws NotFoundException {
    Quiz quiz = quizDao.findById(id).orElseThrow(() -> new NotFoundException(QUIZ_NOT_FOUND));
    List<Integer> questions = quiz.getQuestionsIds();
    return quizInterface.getQuestionsFromId(questions).getBody();
  }

  public Integer calculateResult(List<UserResponse> userResponses) throws NotFoundException {
    return quizInterface.getScore(userResponses).getBody();
  }
}
