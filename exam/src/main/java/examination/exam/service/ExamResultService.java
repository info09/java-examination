package examination.exam.service;

import examination.exam.dto.PageResponse;
import examination.exam.dto.exam_result.*;
import examination.exam.entity.exam_aggregate.ExamRepository;
import examination.exam.entity.exam_result_aggregate.AnswerResult;
import examination.exam.entity.exam_result_aggregate.ExamResult;
import examination.exam.entity.exam_result_aggregate.ExamResultRepository;
import examination.exam.entity.exam_result_aggregate.QuestionResult;
import examination.exam.entity.question_aggregate.Answer;
import examination.exam.entity.question_aggregate.Question;
import examination.exam.exception.AppException;
import examination.exam.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExamResultService {
    private final ExamResultRepository examResultRepository;
    private final ExamRepository examRepository;

    public ExamResultDto getById(String id){
        var examResult = examResultRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.EXAM_RESULT_NOT_EXISTED));
        return examResult.getExamResultDto();
    }

    public List<ExamResultDto> getAll(){
        var examResult = examResultRepository.findAll();
        return examResult.stream().map(ExamResult::getExamResultDto).toList();
    }

    public PageResponse<ExamResultDto> getAllPaging(ExamResultSearch search){
        Pageable pageable = PageRequest.of(search.getPageIndex() - 1, search.getPageSize());
        ExamResult examResultExample = new ExamResult();
        examResultExample.setExamTitle(search.getKeyword());
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues().withMatcher("examTitle", ExampleMatcher.GenericPropertyMatchers.contains());
        Example<ExamResult> example = Example.of(examResultExample, matcher);

        var list = examResultRepository.findAll(example, pageable);

        return PageResponse.<ExamResultDto>builder()
                .currentPage(search.getPageIndex())
                .totalPage(list.getTotalPages())
                .pageSize(search.getPageSize())
                .totalElement(list.getTotalElements())
                .data(list.stream().map(ExamResult::getExamResultDto).toList()).build();
    }

    public ExamResultDto startExam(StartExamRequest request){
        var userId = "123";
        var exam = examRepository.findById(request.getExamId()).orElseThrow(() -> new AppException(ErrorCode.EXAM_NOT_EXISTED));
        var examResult = new ExamResult(userId, request.getExamId());
        examResult.setExamTitle(exam.getName());
        if(exam.isTimeRestricted()){
            var duration = exam.getDuration().split(":");
            var durationTimeSpan = Duration.ofMinutes(Integer.parseInt(duration[0]) * 60L + Integer.parseInt(duration[1]));
            examResult.setExamFinishDate(Instant.now().plus(durationTimeSpan));
        }

        examResult.setCorrectQuestionCount(0);
        var questionResult = exam.getQuestions().stream().map(Question::getQuestionResult).toList();
        examResult.setQuestionResults(questionResult);
        examResult.setFinished(false);
        examResult = examResultRepository.save(examResult);
        return examResult.getExamResultDto();
    }

    public Void skipExam(SkipExamRequest request){
        var examResult = examResultRepository.findById(request.getExamResultId()).orElseThrow(() -> new AppException(ErrorCode.EXAM_RESULT_NOT_EXISTED));

        examResultRepository.delete(examResult);
        return null;
    }

    public ExamResultDto submitQuestion(NextQuestionRequest request){
        var examResult = examResultRepository.findById(request.getExamResultId()).orElseThrow(() -> new AppException(ErrorCode.EXAM_RESULT_NOT_EXISTED));
        var question = examResult.getQuestionResults().stream()
                .filter(x -> x.getId().equals(request.getQuestionId()))
                .findFirst().orElseThrow(() -> new AppException(ErrorCode.QUESTION_NOT_EXISTED));

        for (AnswerResult item : question.getAnswers()) {
            item.setUserChosen(request.getAnswerIds() != null && request.getAnswerIds().contains(item.getId()));
        }

        examResultRepository.save(examResult);
        return examResult.getExamResultDto();
    }

    public ExamResultDto finishExam(FinishExamRequest request){
        var examResult = examResultRepository.findById(request.getExamResultId()).orElseThrow(() -> new AppException(ErrorCode.EXAM_RESULT_NOT_EXISTED));
        var exam = examRepository.findById(examResult.getExamId()).orElseThrow(() -> new AppException(ErrorCode.EXAM_NOT_EXISTED));
        if(exam.isTimeRestricted() && Instant.now().isAfter(examResult.getExamFinishDate())){
            throw new AppException(ErrorCode.EXAM_TIME_OUT);
        }

        examResult.setCorrectQuestionCount(0);

        for (QuestionResult item : examResult.getQuestionResults()) {
            var question = exam.getQuestions().stream().filter(x -> x.getId().equals(item.getId())).findFirst().orElse(null);
            if(question != null){
                List<String> correctAnswers = question.getAnswers().stream()
                        .filter(x -> x.isCorrect())
                        .map(Answer::getId).toList();

                List<String> userAnswers = item.getAnswers().stream()
                        .filter(x -> x.isUserChosen())
                        .map(AnswerResult::getId).toList();

                List<String> inFirstOnly = correctAnswers.stream()
                        .filter(answer -> !userAnswers.contains(answer)).toList();

                List<String> inSecondOnly = userAnswers.stream()
                        .filter(answer -> !correctAnswers.contains(answer)).toList();

                boolean allInBoth = inFirstOnly.isEmpty() && inSecondOnly.isEmpty();

                if (allInBoth) {
                    examResult.setCorrectQuestionCount(examResult.getCorrectQuestionCount() + 1);
                    item.setResult(true);
                } else {
                    item.setResult(false);
                }
            }
        }

        examResult.setPassed(examResult.getCorrectQuestionCount() >= exam.getNumberOfQuestionCorrectForPass());

        examResult.setFinished(true);
        examResultRepository.save(examResult);
        return examResult.getExamResultDto();
    }
}
