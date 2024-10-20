package examination.exam.entity.exam_result_aggregate;

import examination.exam.dto.exam_result.AnswerResultDto;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document("answer_result")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnswerResult {
    @MongoId
    private String id;
    private String content;
    private boolean userChosen;
    private boolean isCorrect;

    public AnswerResultDto getAnswerResultDto(){
        return AnswerResultDto.builder()
                .id(id)
                .content(content)
                .userChosen(userChosen)
                .isCorrect(isCorrect)
                .build();
    }
}
