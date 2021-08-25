package somesh.github.io.fileconsumer.app.student.sheet;

import org.apache.poi.sl.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import somesh.github.io.fileconsumer.app.processor.SheetParser;
import somesh.github.io.fileconsumer.app.shared.SheetHandler;
import somesh.github.io.fileconsumer.app.student.dto.StudentDto;
import somesh.github.io.fileconsumer.app.student.service.StudentService;

import java.util.List;

@Component
public class StudentMarkSheetHandler implements SheetHandler {

  @Autowired
  private SheetParser sheetParser;

  @Autowired
  private StudentService studentService;

  public void processFile(Sheet sheet){
    final List<StudentDto> studentDataList = this.sheetParser.parseFile(sheet, StudentDto.class);

    if (studentDataList.isEmpty()){
      return;
    }else {
      studentDataList.stream().forEach(dto->studentService.saveData(dto));
    }

  }

  @Override public String getSheetName() {
    return null;
  }

  @Override public void processSheet(org.apache.poi.ss.usermodel.Sheet sheet) {

  }
}
