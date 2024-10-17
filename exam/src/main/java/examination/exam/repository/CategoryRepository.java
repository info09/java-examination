package examination.exam.repository;

import examination.exam.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {
    Optional<Category> findByName(String name);
    Page<Category> findByName(String name, Pageable pageable);
}
