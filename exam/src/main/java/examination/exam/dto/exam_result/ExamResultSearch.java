package examination.exam.dto.exam_result;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExamResultSearch {
    private String keyword;
    private int pageIndex;
    private int pageSize;
}
