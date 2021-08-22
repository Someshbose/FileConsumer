package somesh.github.io.fileconsumer.app.student.sheet;

import org.apache.poi.sl.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import somesh.github.io.fileconsumer.app.shared.SheetParser;
import somesh.github.io.fileconsumer.app.student.service.StudentService;
import somesh.github.io.fileconsumer.domain.model.StudentData;

import java.util.List;

@Component
public class StudentMarkSheetHandler {

  @Autowired
  private SheetParser sheetParser;

  @Autowired
  private StudentService studentService;

  public void processFile(Sheet sheet){
    final List<StudentData> studentDataList = this.sheetParser.parseFile(sheet,StudentData.class);

    if (studentDataList.isEmpty()){
      return;
    }else {
      studentDataList.stream().forEach(dto->studentService.saveData(dto));
    }

  }

}
