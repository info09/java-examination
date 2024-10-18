package examination.exam.entity.category_aggregate;

import examination.exam.dto.categories.CategoryDto;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document("category")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @MongoId
    private String id;
    private String name;
    private String urlPath;

    public CategoryDto getCategoryDto(){
        return CategoryDto.builder().id(id).name(name).urlPath(urlPath).build();
    }
}
