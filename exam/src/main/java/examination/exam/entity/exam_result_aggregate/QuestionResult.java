package examination.exam.entity.exam_result_aggregate;

import examination.exam.dto.enums.Level;
import examination.exam.dto.enums.QuestionType;
import examination.exam.dto.exam_result.QuestionResultDto;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Collection;
import java.util.List;

@Document("question_result")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionResult {
    @MongoId
    private String id;
    private String content;
    private QuestionType questionType;
    private Level level;
    private String explain;
    private List<AnswerResult> answers;
    private boolean result;

    public QuestionResultDto getQuestionResultDto(){
        return QuestionResultDto.builder()
                .id(id)
                .content(content)
                .questionType(questionType)
                .level(level)
                .explain(explain)
                .answers(answers.stream().map(AnswerResult::getAnswerResultDto).toList())
                .result(result)
                .build();
    }
}
