package examination.exam.repository;

import examination.exam.entity.ExamResult;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamResultRepository extends MongoRepository<ExamResult, String> {
}
