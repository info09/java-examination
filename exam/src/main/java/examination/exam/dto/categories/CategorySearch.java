package examination.exam.dto.categories;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategorySearch{
    private String name;
    private int pageIndex;
    private int pageSize;
}
