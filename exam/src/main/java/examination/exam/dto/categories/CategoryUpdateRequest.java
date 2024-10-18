package examination.exam.dto.categories;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryUpdateRequest {
    private String name;
    private String urlPath;
}
