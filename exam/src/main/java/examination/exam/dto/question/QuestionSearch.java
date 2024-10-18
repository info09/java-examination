package examination.exam.dto.question;

import examination.exam.dto.enums.Level;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionSearch {
    private String categoryId;
    private Level level;
    private int pageIndex;
    private int pageSize;
}
