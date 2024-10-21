package examination.exam.controller;

import examination.exam.dto.ApiResponse;
import examination.exam.dto.PageResponse;
import examination.exam.dto.enums.Level;
import examination.exam.dto.exam_result.*;
import examination.exam.service.ExamResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/examResult")
public class ExamResultController {
    private final ExamResultService examResultService;

    @GetMapping("/{id}")
    public ApiResponse<ExamResultDto> getExamResultById(@PathVariable String id) {
        return ApiResponse.<ExamResultDto>builder().result(examResultService.getById(id)).build();
    }

    @PostMapping("/finish")
    public ApiResponse<ExamResultDto> finishExam(@RequestBody FinishExamRequest request) {
        return ApiResponse.<ExamResultDto>builder().result(examResultService.finishExam(request)).build();
    }

    @PutMapping("/skip")
    public ApiResponse<ExamResultDto> skipExam(@RequestBody SkipExamRequest request) {
        examResultService.skipExam(request);
        return ApiResponse.<ExamResultDto>builder().build();
    }

    @PostMapping("/next-question")
    public ApiResponse<ExamResultDto> submitQuestion(@RequestBody NextQuestionRequest request) {
        return ApiResponse.<ExamResultDto>builder().result(examResultService.submitQuestion(request)).build();
    }

    @PostMapping("/start")
    public ApiResponse<ExamResultDto> startExam(@RequestBody StartExamRequest request) {
        return ApiResponse.<ExamResultDto>builder().result(examResultService.startExam(request)).build();
    }

    @GetMapping("/paging")
    public ApiResponse<PageResponse<ExamResultDto>> getAllPaging(@RequestParam(required = false) String keyword,
                                                                 @RequestParam(defaultValue = "0") int page,
                                                                 @RequestParam(defaultValue = "10") int size) {
        var search = ExamResultSearch.builder()
                .keyword(keyword)
                .pageIndex(page)
                .pageSize(size)
                .build();
        return ApiResponse.<PageResponse<ExamResultDto>>builder().result(examResultService.getAllPaging(search)).build();
    }
}
