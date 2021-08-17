package somesh.github.io.fileconsumer.domain.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class StudentData {

    @Id
    private Long id;

    private String name;

    private String subject;
}
