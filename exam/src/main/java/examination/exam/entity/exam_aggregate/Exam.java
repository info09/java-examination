package examination.exam.entity.exam_aggregate;

import examination.exam.entity.question_aggregate.Question;
import examination.exam.dto.enums.Level;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.Instant;
import java.util.Collection;
import java.util.Date;

@Document("exam")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Exam {
    @MongoId
    private String id;
    private String name;
    private String sortDescription;
    private int numberOfQuestions;
    private String duration;
    private Collection<Question> questions;
    private Level level;
    private Instant dateCreated;
    private String ownerUserId;
    private int numberOfQuestionCorrectForPass;
    private boolean isTimeRestricted;
    private String categoryName;
}
