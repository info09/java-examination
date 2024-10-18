package examination.exam.entity.exam_result_aggregate;

import examination.exam.dto.enums.Level;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Collection;

@Document("question_result")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionResult {
    @MongoId
    private String id;
    private String content;
    private Level level;
    private String explain;
    private Collection<AnswerResult> answers;
    private boolean result;
}
