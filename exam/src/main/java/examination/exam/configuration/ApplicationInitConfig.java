package examination.exam.configuration;

import examination.exam.dto.enums.Level;
import examination.exam.dto.enums.QuestionType;
import examination.exam.entity.category_aggregate.Category;
import examination.exam.entity.category_aggregate.CategoryRepository;
import examination.exam.entity.exam_aggregate.Exam;
import examination.exam.entity.exam_aggregate.ExamRepository;
import examination.exam.entity.question_aggregate.Answer;
import examination.exam.entity.question_aggregate.Question;
import examination.exam.entity.question_aggregate.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class ApplicationInitConfig {
    @Bean
    @ConditionalOnProperty(prefix = "spring.data.mongodb", value = "uri")
    ApplicationRunner applicationRunner(ExamRepository examRepository, QuestionRepository questionRepository, CategoryRepository categoryRepository) {
        log.info("Initializing application.....");
        return args -> {
            var categoryId1 = UUID.randomUUID().toString();
            var categoryId2 = UUID.randomUUID().toString();
            var categoryId3 = UUID.randomUUID().toString();
            var categoryId4 = UUID.randomUUID().toString();
            var categoryId5 = UUID.randomUUID().toString();
            if(categoryRepository.count() == 0) {
                categoryRepository.saveAll(List.of(
                        Category.builder().id(categoryId1).name("Category 1").urlPath("category-1").build(),
                        Category.builder().id(categoryId2).name("Category 2").urlPath("category-2").build(),
                        Category.builder().id(categoryId3).name("Category 3").urlPath("category-3").build(),
                        Category.builder().id(categoryId4).name("Category 4").urlPath("category-4").build(),
                        Category.builder().id(categoryId5).name("Category 5").urlPath("category-5").build())
                );
            }
            if(questionRepository.count() == 0){
                questionRepository.saveAll(getListQuestion(categoryId1));
            }
            if(examRepository.count() == 0){
                examRepository.saveAll(getListExam(categoryId1));
            }
        };
    }

    private List<Question> getListQuestion(String categoryId1){
        return List.of(
                Question.builder().id(UUID.randomUUID().toString())
                        .content("Question 1")
                        .questionType(QuestionType.SingleSelection)
                        .level(Level.Easy)
                        .categoryId(categoryId1)
                        .answers(List.of(
                                Answer.builder()
                                .id(UUID.randomUUID().toString())
                                .content("Answer 1")
                                .isCorrect(true)
                                .build(),
                                Answer.builder()
                                        .id(UUID.randomUUID().toString())
                                        .content("Answer 2")
                                        .build(),
                                Answer.builder()
                                        .id(UUID.randomUUID().toString())
                                        .content("Answer 3")
                                        .build(),
                                Answer.builder()
                                        .id(UUID.randomUUID().toString())
                                        .content("Answer 4")
                                        .build()))
                        .explain("Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...")
                        .build(),
                Question.builder().id(UUID.randomUUID().toString())
                        .content("Question 2")
                        .questionType(QuestionType.SingleSelection)
                        .level(Level.Easy)
                        .categoryId(categoryId1)
                        .answers(List.of(
                                Answer.builder()
                                        .id(UUID.randomUUID().toString())
                                        .content("Answer 1")
                                        .build(),
                                Answer.builder()
                                        .id(UUID.randomUUID().toString())
                                        .content("Answer 2")
                                        .isCorrect(true)
                                        .build(),
                                Answer.builder()
                                        .id(UUID.randomUUID().toString())
                                        .content("Answer 3")
                                        .build(),
                                Answer.builder()
                                        .id(UUID.randomUUID().toString())
                                        .content("Answer 4")
                                        .build()))
                        .explain("Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...")
                        .build(),
                Question.builder().id(UUID.randomUUID().toString())
                        .content("Question 3")
                        .questionType(QuestionType.SingleSelection)
                        .level(Level.Easy)
                        .categoryId(categoryId1)
                        .answers(List.of(
                                Answer.builder()
                                        .id(UUID.randomUUID().toString())
                                        .content("Answer 1")
                                        .build(),
                                Answer.builder()
                                        .id(UUID.randomUUID().toString())
                                        .content("Answer 2")
                                        .build(),
                                Answer.builder()
                                        .id(UUID.randomUUID().toString())
                                        .content("Answer 3")
                                        .isCorrect(true)
                                        .build(),
                                Answer.builder()
                                        .id(UUID.randomUUID().toString())
                                        .content("Answer 4")
                                        .build()))
                        .explain("Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...")
                        .build(),
                Question.builder().id(UUID.randomUUID().toString())
                        .content("Question 3")
                        .questionType(QuestionType.SingleSelection)
                        .level(Level.Easy)
                        .categoryId(categoryId1)
                        .answers(List.of(
                                Answer.builder()
                                        .id(UUID.randomUUID().toString())
                                        .content("Answer 1")
                                        .build(),
                                Answer.builder()
                                        .id(UUID.randomUUID().toString())
                                        .content("Answer 2")
                                        .build(),
                                Answer.builder()
                                        .id(UUID.randomUUID().toString())
                                        .content("Answer 3")
                                        .build(),
                                Answer.builder()
                                        .id(UUID.randomUUID().toString())
                                        .content("Answer 4")
                                        .isCorrect(true)
                                        .build()))
                        .explain("Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...")
                        .build()
                );
    }

    private List<Exam> getListExam(String categoryId1){
        return List.of(
                Exam.builder()
                        .id(UUID.randomUUID().toString())
                        .name("Exam1")
                        .shortDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry")
                        .content("<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.</p>")
                        .numberOfQuestions(5)
                        .duration("10:00")
                        .questions(getListQuestion(categoryId1).subList(0, 4))
                        .level(Level.Easy)
                        .dateCreated(Instant.now())
                        .numberOfQuestionCorrectForPass(3)
                        .isTimeRestricted(true)
                .build(),
                Exam.builder()
                        .name("Exam1")
                        .shortDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry")
                        .content("<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.</p>")
                        .numberOfQuestions(5)
                        .duration("10:00")
                        .questions(getListQuestion(categoryId1).subList(0, 4))
                        .level(Level.Easy)
                        .dateCreated(Instant.now())
                        .numberOfQuestionCorrectForPass(3)
                        .isTimeRestricted(true)
                        .build());
    }
}
