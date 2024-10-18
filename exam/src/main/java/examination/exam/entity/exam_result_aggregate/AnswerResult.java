package examination.exam.entity.exam_result_aggregate;

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
}
