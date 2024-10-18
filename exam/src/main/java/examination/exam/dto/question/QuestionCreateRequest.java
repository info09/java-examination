package examination.exam.dto.question;

import examination.exam.dto.enums.Level;
import examination.exam.dto.enums.QuestionType;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionCreateRequest {
    @NotNull
    private String content;

    @NotNull
    private QuestionType questionType;

    @NotNull
    private Level level;

    @NotNull
    private String categoryId;

    @NotNull
    private List<AnswerDto> answers = new ArrayList<>();

    private String explain;
}
