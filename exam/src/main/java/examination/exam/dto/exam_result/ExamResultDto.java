package examination.exam.dto.exam_result;

import lombok.*;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExamResultDto {
    private String id;
    private String examId;
    private String examTitle;
    private String userId;
    private String email;
    private String fullName;
    private List<QuestionResultDto> questionResults;
    private Instant examStartDate;
    private Instant examFinishDate;
    private Boolean passed;
    private boolean finished;
}
