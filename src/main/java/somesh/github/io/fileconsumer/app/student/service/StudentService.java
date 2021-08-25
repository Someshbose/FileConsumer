package somesh.github.io.fileconsumer.app.student.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import somesh.github.io.fileconsumer.app.student.dto.StudentDto;
import somesh.github.io.fileconsumer.domain.model.StudentData;
import somesh.github.io.fileconsumer.domain.repo.StudentDataRepository;

@Service
public class StudentService {

  @Autowired
  private StudentDataRepository studentDataRepository;

  public void saveData(StudentDto dto){
    StudentData studentData = constructEntityFromDto(dto);
    studentDataRepository.save(studentData);
  }

  private StudentData constructEntityFromDto(StudentDto dto){
    return StudentData.builder()
        .id(dto.getId())
        .name(dto.getStudentName())
        .subject(dto.getStudentName()).build();
  }
}
