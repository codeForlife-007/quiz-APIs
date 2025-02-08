package com.codehelp.quiz_service.controller;

import com.codehelp.quiz_service.dto.QuizDto;
import com.codehelp.quiz_service.exception.NotFoundException;
import com.codehelp.quiz_service.model.QuestionWrapper;
import com.codehelp.quiz_service.model.Quiz;
import com.codehelp.quiz_service.model.UserResponse;
import com.codehelp.quiz_service.service.QuizService;
import jakarta.ws.rs.Path;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("quiz")
@AllArgsConstructor
public class QuizController {

  private QuizService quizService;

  @PostMapping("create")
  public ResponseEntity<Quiz> createQuiz(@RequestBody QuizDto quizDto) {
    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                              .buildAndExpand()
                              .toUri();
    return ResponseEntity.created(location).body(quizService.createQuiz(quizDto.getCategoryName(),
            quizDto.getNumQuestions(), quizDto.getTitle()));
  }

  @GetMapping("get/{id}")
  public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id) throws NotFoundException {
    return ResponseEntity.ok(quizService.getQuizQuestions(id));
  }

  @PostMapping("submit")
  public ResponseEntity<Integer> submitQuiz(@RequestBody List<UserResponse> userResponses) throws NotFoundException {
    return ResponseEntity.ok(quizService.calculateResult(userResponses));
  }
}
