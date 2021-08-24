package somesh.github.io.fileconsumer.app.student.dto;

import lombok.Getter;
import somesh.github.io.fileconsumer.app.shared.ExcelRow;

@Getter
public class StudentDto implements ExcelRow {

  private long id;
  private String studentName;
  private String subject;

  private int rowNum;
  private String sheetName;

}
