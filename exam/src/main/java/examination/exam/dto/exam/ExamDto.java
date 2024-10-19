package examination.exam.dto.exam;

import examination.exam.dto.enums.Level;
import examination.exam.dto.question.QuestionDto;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExamDto {
    private String id;
    private String name;
    private String shortDescription;
    private String content;
    private String duration;
    private int numberOfQuestions;
    private Level level;
    private List<QuestionDto> questions;
    private Instant dateCreated;
    private int numberOfQuestionCorrectForPass;
    private boolean isTimeRestricted;
    private String categoryName;
    private String categoryId;
    private String ownerUserId;
}
