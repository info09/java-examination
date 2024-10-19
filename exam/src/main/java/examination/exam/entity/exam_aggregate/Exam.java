package examination.exam.entity.exam_aggregate;

import examination.exam.dto.exam.ExamDto;
import examination.exam.entity.question_aggregate.Question;
import examination.exam.dto.enums.Level;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Document("exam")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Exam {
    @MongoId
    private String id;
    private String name;
    private String shortDescription;
    private String content;
    private int numberOfQuestions;
    private String duration;
    private List<Question> questions;
    private Level level;
    private Instant dateCreated;
    private String ownerUserId;
    private int numberOfQuestionCorrectForPass;
    private boolean isTimeRestricted;
    private String categoryId;
    private String categoryName;

    public ExamDto getExamDto() {
        return ExamDto.builder()
                .id(id)
                .name(name)
                .shortDescription(shortDescription)
                .content(content)
                .numberOfQuestions(numberOfQuestions)
                .duration(duration)
                .questions(questions.stream().map(Question::getQuestionDto).toList())
                .level(level)
                .dateCreated(dateCreated)
                .ownerUserId(ownerUserId)
                .numberOfQuestionCorrectForPass(numberOfQuestionCorrectForPass)
                .isTimeRestricted(isTimeRestricted)
                .categoryName(categoryName)
                .categoryId(categoryId)
                .build();
    }
}
