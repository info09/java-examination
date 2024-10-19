package examination.exam.dto.exam_result;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NextQuestionRequest {
    private String examResultId;
    private String questionId;
    private List<String> answerIds;
}
