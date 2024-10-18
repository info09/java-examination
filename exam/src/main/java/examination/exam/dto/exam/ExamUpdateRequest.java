package examination.exam.dto.exam;

import examination.exam.dto.enums.Level;
import examination.exam.dto.question.QuestionDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExamUpdateRequest {
    private String id;
    private String name;
    private String shortDescription;
    private String content;
    private int numberOfQuestions;
    private String duration;
    private Level level;
    private List<QuestionDto> questions;
    private int numberOfQuestionCorrectForPass;
    private boolean isTimeRestricted;
    private boolean autoGenerateQuestions;
    private String categoryId;
}
