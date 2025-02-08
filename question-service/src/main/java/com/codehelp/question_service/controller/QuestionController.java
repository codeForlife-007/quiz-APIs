package com.codehelp.question_service.controller;

import com.codehelp.question_service.dto.QuestionDto;
import com.codehelp.question_service.exception.NotFoundException;
import com.codehelp.question_service.model.Question;
import com.codehelp.question_service.model.QuestionWrapper;
import com.codehelp.question_service.model.UserResponse;
import com.codehelp.question_service.service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("question")
@AllArgsConstructor
public class QuestionController {

  private QuestionService questionService;

  private Environment environment;

  @GetMapping("allQuestions")
  public ResponseEntity<List<Question>> getAllQuestions() {
    return ResponseEntity.ok(questionService.getAllQuestions());
  }

  @GetMapping("category/{category}")
  public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category) {
    return ResponseEntity.ok(questionService.getQuestionsByCategory(category));
  }

  @PostMapping("create")
  public ResponseEntity<Question> createQuestion(@RequestBody QuestionDto questionDto) {
    Question question = questionService.createQuestion(questionDto);
    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                                       .buildAndExpand()
                                       .toUri();
    return ResponseEntity.created(location).body(question);
  }

  @DeleteMapping("delete/{id}")
  public ResponseEntity<Void> deleteQuestion(@PathVariable int id) throws NotFoundException {
    questionService.deleteQuestion(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("update/{id}")
  public ResponseEntity<Question> updateQuestion(@PathVariable int id, @RequestBody QuestionDto questionDto) throws NotFoundException {
    return ResponseEntity.ok(questionService.updateQuestion(id, questionDto));
  }

  // quiz service calls
  @GetMapping("generate")
  public ResponseEntity<List<Integer>> getQuestionsForQuiz(
          @RequestParam String categoryName, @RequestParam Integer noOfQue) {
    return ResponseEntity.ok(questionService.getQuestionsForQuiz(categoryName, noOfQue));
  }

  @PostMapping("getQuestions")
  public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds)
          throws NotFoundException {
    System.out.println(environment.getProperty("local.server.port"));
    return ResponseEntity.ok(questionService.getQuestionsFromIds(questionIds));
  }

  @PostMapping("getScore")
  public ResponseEntity<Integer> getScore(@RequestBody List<UserResponse> userResponses) throws NotFoundException {
    return ResponseEntity.ok(questionService.getScore(userResponses));
  }
}
