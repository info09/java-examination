package examination.exam.dto.question;

import examination.exam.entity.question_aggregate.Answer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class AnswerDto {
    private String id;
    private String content;
    private boolean isCorrect;

    public Answer toAnswer() {
        return Answer.builder()
                .id(id)
                .content(content)
                .isCorrect(isCorrect)
                .build();
    }
}
