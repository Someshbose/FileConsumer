package somesh.github.io.fileconsumer.app.processor;

import lombok.Getter;
import somesh.github.io.fileconsumer.domain.model.FileUploadedStatus;

@Getter
public class FileDto {

  private Long id;
  private String fileReferenceId;
  private String fileTypeCode;
  private String fileName;
  private String charSet;
  private String submitterEmail;
  private String fileContent;
  private FileUploadedStatus status;
  private String correlationId;
  private String fileExtension;
  private String fieldSeparator;
}
