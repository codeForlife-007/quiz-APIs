package com.codehelp.question_service.service;

import com.codehelp.question_service.dao.QuestionDao;
import com.codehelp.question_service.dto.QuestionDto;
import com.codehelp.question_service.exception.NotFoundException;
import com.codehelp.question_service.model.Question;
import com.codehelp.question_service.model.QuestionWrapper;
import com.codehelp.question_service.model.UserResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class QuestionService {

  private static final String QUESTION_NOT_FOUND = "Question not found";

  private QuestionDao questionDao;

  public List<Question> getAllQuestions() {
    return questionDao.findAll();
  }

  public List<Question> getQuestionsByCategory(String category) {
    return questionDao.findByCategory(category);
  }

  public Question createQuestion(QuestionDto questionDto) {
    Question question = questionDto.toEntity();
    question.setQuestionTitle(questionDto.getQuestionTitle());
    question.setOption1(questionDto.getOption1());
    question.setOption2(questionDto.getOption2());
    question.setOption3(questionDto.getOption3());
    question.setOption4(questionDto.getOption4());
    question.setRightAnswer(questionDto.getRightAnswer());
    question.setDifficultyLevel(questionDto.getDifficultyLevel());
    question.setCategory(questionDto.getCategory());
    return questionDao.save(question);
  }

  public void deleteQuestion(int id) throws NotFoundException {
    Question question = questionDao.findById(id).orElseThrow(() -> new NotFoundException(QUESTION_NOT_FOUND));
    questionDao.delete(question);
  }

  public Question updateQuestion(int id, QuestionDto questionDto) throws NotFoundException {
    Question question = questionDao.findById(id).orElseThrow(() -> new NotFoundException(QUESTION_NOT_FOUND));
    question.setQuestionTitle(questionDto.getQuestionTitle());
    question.setOption1(questionDto.getOption1());
    question.setOption2(questionDto.getOption2());
    question.setOption3(questionDto.getOption3());
    question.setOption4(questionDto.getOption4());
    question.setRightAnswer(questionDto.getRightAnswer());
    question.setDifficultyLevel(questionDto.getDifficultyLevel());
    question.setCategory(questionDto.getCategory());
    return questionDao.save(question);
  }

  public List<Integer> getQuestionsForQuiz(String categoryName, Integer noOfQue) {
    return questionDao.findRandomQuestionsByCategory(categoryName, noOfQue);
  }

  public List<QuestionWrapper> getQuestionsFromIds(List<Integer> questionIds) throws NotFoundException {
    List<QuestionWrapper> questionWrappers = new ArrayList<>();
    List<Question> questions = new ArrayList<>();

    for (Integer id : questionIds) {
      questions.add(questionDao.findById(id).orElseThrow(() -> new NotFoundException(QUESTION_NOT_FOUND)));
    }

    for (Question question : questions) {
      questionWrappers.add(new QuestionWrapper(question.getId(), question.getQuestionTitle(), question.getOption1(),
              question.getOption2(), question.getOption3(), question.getOption4()));
    }
    return questionWrappers;
  }

  public Integer getScore(List<UserResponse> userResponses) throws NotFoundException {
    int score = 0;
    for (UserResponse userResponse : userResponses) {
      // it's good but every iteration have database interaction, which is not good
      Question question =
              questionDao.findById(userResponse.getQuestionId()).orElseThrow(() -> new NotFoundException(QUESTION_NOT_FOUND));
      if (userResponse.getUserResponse().equals(question.getRightAnswer())) {
        score++;
      }
    }
    return score;
  }
}
