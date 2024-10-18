package examination.exam.entity.question_aggregate;

import examination.exam.dto.enums.Level;
import examination.exam.dto.enums.QuestionType;
import examination.exam.dto.question.QuestionDto;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.Instant;
import java.util.List;

@Document("question")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    @MongoId
    private String id;
    private String content;
    private QuestionType questionType;
    private Level level;
    private String categoryId;
    private String categoryName;
    private List<Answer> answers;
    private String explain;
    private Instant dateCreated;
    private String ownerUserId;

    public QuestionDto getQuestionDto() {
        return QuestionDto.builder()
                .id(id)
                .content(content)
                .questionType(questionType)
                .level(level)
                .categoryId(categoryId)
                .categoryName(categoryName)
                .answers(answers.stream().map(Answer::getAnswerDto).toList())
                .explain(explain)
                .dateCreated(dateCreated)
                .ownerUserId(ownerUserId)
                .build();
    }
}
