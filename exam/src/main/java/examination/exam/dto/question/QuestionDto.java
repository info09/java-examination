package examination.exam.dto.question;

import examination.exam.dto.enums.Level;
import examination.exam.dto.enums.QuestionType;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionDto {
    private String id;
    private String content;
    private QuestionType questionType;
    private Level level;
    private String categoryId;
    private List<AnswerDto> answers;
    private String explain;
    private Instant dateCreated;
    private String ownerUserId;
    private String categoryName;
}
