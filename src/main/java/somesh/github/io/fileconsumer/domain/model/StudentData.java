package somesh.github.io.fileconsumer.domain.model;

import lombok.Builder;
import lombok.Getter;
import somesh.github.io.fileconsumer.domain.shared.AbstractBaseEntity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Builder
public class StudentData extends AbstractBaseEntity<StudentData> {

    @Id
    private Long id;

    private String name;

    private String subject;
}
