package examination.exam.service;

import examination.exam.dto.PageResponse;
import examination.exam.dto.exam.ExamCreateRequest;
import examination.exam.dto.exam.ExamDto;
import examination.exam.dto.exam.ExamSearch;
import examination.exam.dto.exam.ExamUpdateRequest;
import examination.exam.dto.question.QuestionDto;
import examination.exam.dto.question.QuestionSearch;
import examination.exam.entity.exam_aggregate.Exam;
import examination.exam.entity.exam_aggregate.ExamRepository;
import examination.exam.entity.question_aggregate.Question;
import examination.exam.exception.AppException;
import examination.exam.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExamService {
    private final ExamRepository examRepository;

    public ExamDto getExamById(String id) {
        var exam = examRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.EXAM_NOT_EXISTED));

        return exam.getExamDto();
    }

    public List<ExamDto> getAllExams() {
        List<Exam> exams = examRepository.findAll();
        return exams.stream().map(Exam::getExamDto).toList();
    }

    public ExamDto createExam(ExamCreateRequest request) {
        var exam = Exam.builder()
                .name(request.getName())
                .content(request.getContent())
                .numberOfQuestions(request.getNumberOfQuestions())
                .duration(request.getDuration())
                .level(request.getLevel())
                .numberOfQuestionCorrectForPass(request.getNumberOfQuestionCorrectForPass())
                .isTimeRestricted(request.isTimeRestricted())
                .categoryId(request.getCategoryId())
                .build();

        exam.setQuestions(request.getQuestions().stream().map(QuestionDto::toQuestion).collect(Collectors.toList()));
        examRepository.save(exam);
        return exam.getExamDto();
    }

    public ExamDto updateExam(ExamUpdateRequest request) {
        var exam = examRepository.findById(request.getId()).orElseThrow(() -> new AppException(ErrorCode.EXAM_NOT_EXISTED));
        exam.setName(request.getName());
        exam.setContent(request.getContent());
        exam.setNumberOfQuestions(request.getNumberOfQuestions());
        exam.setDuration(request.getDuration());
        exam.setLevel(request.getLevel());
        exam.setNumberOfQuestionCorrectForPass(request.getNumberOfQuestionCorrectForPass());
        exam.setTimeRestricted(request.isTimeRestricted());
        exam.setCategoryId(request.getCategoryId());
        exam.setQuestions(request.getQuestions().stream().map(QuestionDto::toQuestion).collect(Collectors.toList()));
        examRepository.save(exam);
        return exam.getExamDto();
    }

    public void deleteExam(String id) {
        examRepository.deleteById(id);
    }

    public PageResponse<ExamDto> getExamsPaging(ExamSearch search){
        Pageable pageable = PageRequest.of(search.getPageIndex() - 1, search.getPageSize());

        Exam examExample = new Exam();
        examExample.setCategoryId(search.getCategoryId());
        examExample.setName(search.getSearchKeyword());

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues().withMatcher("categoryId", ExampleMatcher.GenericPropertyMatchers.regex())
                .withIgnoreNullValues().withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains());
        Example<Exam> example = Example.of(examExample, matcher);

        var list = examRepository.findAll(example, pageable);

        return PageResponse.<ExamDto>builder()
                .currentPage(search.getPageIndex())
                .totalPage(list.getTotalPages())
                .pageSize(search.getPageSize())
                .totalElement(list.getTotalElements())
                .data(list.stream().map(Exam::getExamDto).collect(Collectors.toList())).build();
    }
}
