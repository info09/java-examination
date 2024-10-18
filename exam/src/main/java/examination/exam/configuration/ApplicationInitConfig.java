package examination.exam.configuration;

import examination.exam.entity.category_aggregate.Category;
import examination.exam.entity.category_aggregate.CategoryRepository;
import examination.exam.entity.exam_aggregate.ExamRepository;
import examination.exam.entity.question_aggregate.Question;
import examination.exam.entity.question_aggregate.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class ApplicationInitConfig {
    @Bean
    @ConditionalOnProperty(prefix = "spring", value = "datasource.driverClassName")
    ApplicationRunner applicationRunner(ExamRepository examRepository, QuestionRepository questionRepository, CategoryRepository categoryRepository) {
        log.info("Initializing application.....");
        return args -> {
            if(categoryRepository.count() == 0) {
                categoryRepository.saveAll(List.of(
                        Category.builder().name("Category 1").urlPath("category-1").build(),
                        Category.builder().name("Category 2").urlPath("category-2").build(),
                        Category.builder().name("Category 3").urlPath("category-3").build(),
                        Category.builder().name("Category 4").urlPath("category-4").build(),
                        Category.builder().name("Category 5").urlPath("category-5").build())
                );
            }
        };
    }
}
