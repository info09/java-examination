package examination.exam.controller;

import examination.exam.dto.ApiResponse;
import examination.exam.dto.PageResponse;
import examination.exam.dto.enums.Level;
import examination.exam.dto.question.QuestionCreateRequest;
import examination.exam.dto.question.QuestionDto;
import examination.exam.dto.question.QuestionSearch;
import examination.exam.dto.question.QuestionUpdateRequest;
import examination.exam.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/question")
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping("/random")
    public ApiResponse<List<QuestionDto>> getRandomQuestionsForExamAsync(String categoryId, Level level, int numberOfQuestions) {
        return ApiResponse.<List<QuestionDto>>builder().result(questionService.getRandomQuestionsForExamAsync(categoryId, level, numberOfQuestions)).build();
    }

    @GetMapping("/paging")
    public ApiResponse<PageResponse<QuestionDto>> getCategoriesPaging(@RequestParam(required = false) String categoryId,
                                                                      @RequestParam(required = false) Level level,
                                                                      @RequestParam(defaultValue = "0") int page,
                                                                      @RequestParam(defaultValue = "10") int size) {
        var search = QuestionSearch.builder()
                .categoryId(categoryId)
                .level(level)
                .pageIndex(page)
                .pageSize(size)
                .build();
        return ApiResponse.<PageResponse<QuestionDto>>builder().result(questionService.getCategoriesPaging(search)).build();
    }

    @GetMapping("/list")
    public ApiResponse<List<QuestionDto>> getAllQuestions() {
        return ApiResponse.<List<QuestionDto>>builder().result(questionService.getAllQuestions()).build();
    }

    @GetMapping("/{id}")
    public ApiResponse<QuestionDto> getQuestionById(@PathVariable  String id) {
        return ApiResponse.<QuestionDto>builder().result(questionService.getQuestionById(id)).build();
    }

    @PostMapping()
    public ApiResponse<QuestionDto> createQuestion(@RequestBody QuestionCreateRequest request) {
        return ApiResponse.<QuestionDto>builder().result(questionService.createQuestion(request)).build();
    }

    @PutMapping("/{id}")
    public ApiResponse<QuestionDto> updateQuestion(@RequestBody QuestionUpdateRequest request) {
        return ApiResponse.<QuestionDto>builder().result(questionService.updateQuestion(request)).build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteQuestion(@PathVariable String id) {
        questionService.deleteQuestion(id);
        return ApiResponse.<Void>builder().build();
    }
}
