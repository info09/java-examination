package examination.exam.entity.exam_result_aggregate;

import examination.exam.dto.exam_result.ExamResultDto;
import examination.exam.entity.question_aggregate.Question;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Document("exam_result")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExamResult {
    @MongoId
    private String id;
    private String examId;
    private String examTitle;
    private String userId;
    private String email;
    private String fullName;
    private List<QuestionResult> questionResults;
    private int correctQuestionCount;
    private Instant examStartDate;
    private Instant examFinishDate;
    private boolean passed;
    private boolean finished;

    public ExamResult(String userId, String examId){
        this.userId = userId;
        this.examId = examId;
        this.examStartDate = Instant.now();
        this.finished = false;
    }

    public ExamResultDto getExamResultDto(){
        return ExamResultDto.builder()
                .id(id)
                .examId(examId)
                .examTitle(examTitle)
                .userId(userId)
                .email(email)
                .fullName(fullName)
                .questionResults(questionResults.stream().map(QuestionResult::getQuestionResultDto).toList())
                .examStartDate(examStartDate)
                .examFinishDate(examFinishDate)
                .passed(passed)
                .finished(finished)
                .build();
    }
}
