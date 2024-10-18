package examination.exam.dto.exam;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExamSearch {
    private String categoryId;
    private String searchKeyword;
    private int pageIndex;
    private int pageSize;
}
