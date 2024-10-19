package examination.exam.dto.exam_result;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnswerResultDto {
    private String id;
    private String content;
    private Boolean userChosen; // Using Boolean for nullable boolean
    private boolean isCorrect;
}
