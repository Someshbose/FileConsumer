package somesh.github.io.fileconsumer.domain.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import somesh.github.io.fileconsumer.domain.model.StudentData;

@Repository
public interface StudentDataRepository extends CrudRepository<StudentData, Long> {
}
