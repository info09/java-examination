package examination.exam.entity.exam_result_aggregate;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;

@Document("exam_result")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExamResult {
    @MongoId
    private String id;
    private String examId;
    private String userId;
    private String email;
    private String fullName;
    private Collection<QuestionResult> questionResults;
    private int correctQuestionCount;
    private Instant examStartDate;
    private Instant examFinishDate;
    private boolean passed;
    private boolean finished;
}
