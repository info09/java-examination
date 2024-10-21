package examination.exam.controller;

import examination.exam.dto.ApiResponse;
import examination.exam.dto.PageResponse;
import examination.exam.dto.exam.ExamCreateRequest;
import examination.exam.dto.exam.ExamDto;
import examination.exam.dto.exam.ExamSearch;
import examination.exam.dto.exam.ExamUpdateRequest;
import examination.exam.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/exams")
public class ExamController {
    private final ExamService examService;

    @GetMapping("/paging")
    public ApiResponse<PageResponse<ExamDto>> getExams(@RequestParam(required = false) String categoryId,
                                                       @RequestParam(required = false) String keyword,
                                                       @RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "10") int size) {
        var search = ExamSearch.builder()
                .categoryId(categoryId)
                .searchKeyword(keyword)
                .pageIndex(page)
                .pageSize(size)
                .build();
        return ApiResponse.<PageResponse<ExamDto>>builder().result(examService.getExamsPaging(search)).build();
    }

    @GetMapping("/{id}")
    public ApiResponse<ExamDto> getExamById(@PathVariable String id) {
        return ApiResponse.<ExamDto>builder().result(examService.getExamById(id)).build();
    }

    @GetMapping("/list")
    public ApiResponse<List<ExamDto>> getAllExams() {
        return ApiResponse.<List<ExamDto>>builder().result(examService.getAllExams()).build();
    }

    @PostMapping
    public ApiResponse<ExamDto> createExam(@RequestBody ExamCreateRequest request) {
        return ApiResponse.<ExamDto>builder().result(examService.createExam(request)).build();
    }

    @PutMapping
    public ApiResponse<ExamDto> updateExam(@RequestBody ExamUpdateRequest request) {
        return ApiResponse.<ExamDto>builder().result(examService.updateExam(request)).build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<ExamDto> deleteExam(@PathVariable String id) {
        examService.deleteExam(id);
        return ApiResponse.<ExamDto>builder().build();
    }
}
