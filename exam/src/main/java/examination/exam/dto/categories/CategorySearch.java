package examination.exam.dto.categories;

import examination.exam.dto.PagingParameters;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategorySearch extends PagingParameters {
    private String name;
}
