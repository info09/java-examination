package examination.exam.entity;

import examination.exam.entity.enums.Level;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

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
    private LocalDate dateCreated;
    private String ownerUserId;
    private int numberOfQuestionCorrectForPass;
    private boolean isTimeRestricted;
    private String categoryName;
}
