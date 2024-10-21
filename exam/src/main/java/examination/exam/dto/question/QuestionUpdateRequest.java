package examination.exam.dto.question;

import examination.exam.dto.enums.Level;
import examination.exam.dto.enums.QuestionType;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionUpdateRequest {
    private String id;
    private String content;
    private QuestionType questionType;
    private Level level;
    private String categoryId;
    private List<AnswerDto> answers = new ArrayList<>();
    private String explain;

}
