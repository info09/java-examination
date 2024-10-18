package examination.exam.dto.categories;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryCreateRequest {

    private String name;

    private String urlPath;
}
