package examination.exam.entity.exam_result_aggregate;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamResultRepository extends MongoRepository<ExamResult, String> {
}
