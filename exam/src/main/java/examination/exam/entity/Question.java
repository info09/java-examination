package examination.exam.entity;

import examination.exam.entity.enums.Level;
import examination.exam.entity.enums.QuestionType;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Document("question")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    @MongoId
    private String id;
    private String content;
    private QuestionType questionType;
    private Level level;
    private String categoryId;
    private String categoryName;
    private Collection<Answer> answers;
    private String explain;
    private LocalDate dateCreated;
    private String ownerUserId;
}
