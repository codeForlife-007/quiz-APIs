package com.codehelp.quiz_service.feign;

import com.codehelp.quiz_service.exception.NotFoundException;
import com.codehelp.quiz_service.model.QuestionWrapper;
import com.codehelp.quiz_service.model.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {

  @GetMapping("question/generate")
  public ResponseEntity<List<Integer>> getQuestionsForQuiz(
          @RequestParam String categoryName, @RequestParam Integer noOfQue);

  @PostMapping("question/getQuestions")
  public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds)
          throws NotFoundException;

  @PostMapping("question/getScore")
  public ResponseEntity<Integer> getScore(@RequestBody List<UserResponse> userResponses) throws NotFoundException;
}