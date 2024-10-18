package examination.exam.entity.question_aggregate;

import examination.exam.dto.question.AnswerDto;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document("answer")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Answer {
    @MongoId
    private String id;
    private String content;
    private boolean isCorrect;

    public AnswerDto getAnswerDto() {
        return AnswerDto.builder().id(id).content(content).isCorrect(isCorrect).build();
    }
}
