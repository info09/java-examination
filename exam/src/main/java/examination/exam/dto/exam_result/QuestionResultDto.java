package examination.exam.dto.exam_result;

import examination.exam.dto.enums.Level;
import examination.exam.dto.enums.QuestionType;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionResultDto {
    private String id;
    private String content;
    private QuestionType questionType;
    private Level level;
    private String explain;
    private List<AnswerResultDto> answers;
    private Boolean result;
}
