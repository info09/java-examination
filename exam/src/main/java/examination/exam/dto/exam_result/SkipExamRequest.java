package examination.exam.dto.exam_result;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SkipExamRequest {
    private String examResultId;
}
