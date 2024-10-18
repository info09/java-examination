package examination.exam.service;

import examination.exam.dto.PageResponse;
import examination.exam.dto.categories.CategoryCreateRequest;
import examination.exam.dto.categories.CategoryDto;
import examination.exam.dto.categories.CategorySearch;
import examination.exam.dto.enums.Level;
import examination.exam.dto.question.*;
import examination.exam.entity.category_aggregate.Category;
import examination.exam.entity.question_aggregate.Answer;
import examination.exam.entity.question_aggregate.Question;
import examination.exam.entity.question_aggregate.QuestionRepository;
import examination.exam.exception.AppException;
import examination.exam.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    public List<QuestionDto> getAllQuestions() {
        List<Question> questions = questionRepository.findAll();
        return questions.stream().map(Question::getQuestionDto).collect(Collectors.toList());
    }


    public QuestionDto getQuestionById(String id) {
        var question = questionRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.QUESTION_NOT_EXISTED));
        return question.getQuestionDto();
    }

    public List<QuestionDto> getRandomQuestionsForExamAsync(String categoryId, Level level, int numberOfQuestions) {
        Pageable pageable = PageRequest.of(0, numberOfQuestions);
        Question categoryExample = new Question();
        categoryExample.setCategoryId(categoryId);
        categoryExample.setLevel(level);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues().withMatcher("categoryId", ExampleMatcher.GenericPropertyMatchers.regex())
                .withIgnoreNullValues().withMatcher("level", ExampleMatcher.GenericPropertyMatchers.regex());

        Example<Question> example = Example.of(categoryExample, matcher);

        var list = questionRepository.findAll(example, pageable);

        return list.stream().map(Question::getQuestionDto).collect(Collectors.toList());
    }

    public PageResponse<QuestionDto> getCategoriesPaging(QuestionSearch search){
        Pageable pageable = PageRequest.of(search.getPageIndex() - 1, search.getPageSize());
        Question questionExample = new Question();
        questionExample.setCategoryId(search.getCategoryId());
        questionExample.setLevel(search.getLevel());
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues().withMatcher("categoryId", ExampleMatcher.GenericPropertyMatchers.regex())
                .withIgnoreNullValues().withMatcher("level", ExampleMatcher.GenericPropertyMatchers.regex());
        Example<Question> example = Example.of(questionExample, matcher);

        var list = questionRepository.findAll(example, pageable);

        return PageResponse.<QuestionDto>builder()
                .currentPage(search.getPageIndex())
                .totalPage(list.getTotalPages())
                .pageSize(search.getPageSize())
                .totalElement(list.getTotalElements())
                .data(list.stream().map(Question::getQuestionDto).collect(Collectors.toList())).build();
    }

    public QuestionDto createQuestion(QuestionCreateRequest request) {
        var question = Question.builder()
                .content(request.getContent())
                .questionType(request.getQuestionType())
                .level(request.getLevel())
                .categoryId(request.getCategoryId())
                .explain(request.getExplain())
                .dateCreated(Instant.now())
                .build();

        for (var answer : request.getAnswers()) {
            if(Objects.isNull(answer.getId())){
                answer.setId(UUID.randomUUID().toString());
            }
        }
        question.setAnswers(request.getAnswers().stream().map(AnswerDto::toAnswer).collect(Collectors.toList()));

        question = questionRepository.save(question);
        return question.getQuestionDto();
    }

    public QuestionDto updateQuestion(QuestionUpdateRequest request) {
        var question = questionRepository.findById(request.getId()).orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_EXISTED));

        question.setContent(request.getContent());
        question.setQuestionType(request.getQuestionType());
        question.setLevel(request.getLevel());
        question.setCategoryId(request.getCategoryId());
        question.setExplain(request.getExplain());

        question = questionRepository.save(question);

        return question.getQuestionDto();
    }

    public void deleteQuestion(String id) {
        questionRepository.deleteById(id);
    }
}
