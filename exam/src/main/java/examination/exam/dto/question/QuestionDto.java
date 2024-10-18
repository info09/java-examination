package examination.exam.dto.question;

import examination.exam.dto.enums.Level;
import examination.exam.dto.enums.QuestionType;
import examination.exam.entity.question_aggregate.Question;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionDto {
    private String id;
    private String content;
    private QuestionType questionType;
    private Level level;
    private String categoryId;
    private List<AnswerDto> answers;
    private String explain;
    private Instant dateCreated;
    private String ownerUserId;
    private String categoryName;

    public Question toQuestion() {
        return Question.builder()
                .id(id)
                .content(content)
                .questionType(questionType)
                .level(level)
                .categoryId(categoryId)
                .explain(explain)
                .dateCreated(dateCreated)
                .ownerUserId(ownerUserId)
                .build();
    }
}
