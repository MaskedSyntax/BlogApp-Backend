package com.maskedsyntax.queriously.controller;

import com.maskedsyntax.queriously.dto.QuestionRequestDTO;
import com.maskedsyntax.queriously.dto.QuestionResponseDTO;
import com.maskedsyntax.queriously.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {
    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<QuestionResponseDTO> addQuestion(@RequestBody QuestionRequestDTO questionRequestDTO) {
        QuestionResponseDTO questionResponseDTO = questionService.saveQuestion(questionRequestDTO);
        return ResponseEntity.ok(questionResponseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionResponseDTO> getQuestionById(@PathVariable Long id) {
        QuestionResponseDTO questionResponseDTO = questionService.getQuestionById(id);
        return new ResponseEntity<>(questionResponseDTO, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<QuestionResponseDTO>> getAllQuestions() {
        List<QuestionResponseDTO> questions = questionService.getQuestions();
        return ResponseEntity.ok(questions);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<QuestionResponseDTO> updateQuestion(@PathVariable Long id,
            @RequestBody QuestionRequestDTO questionRequestDTO) {
        QuestionResponseDTO questionResponseDTO = questionService.updateQuestion(id, questionRequestDTO);
        return ResponseEntity.ok(questionResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAllQuestions(@PathVariable Long id) {
        questionService.deleteQuestion(id);
        return ResponseEntity.noContent().build();
    }
}
