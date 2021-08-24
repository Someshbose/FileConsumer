package somesh.github.io.fileconsumer.app.service.processor;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import somesh.github.io.fileconsumer.app.service.messaging.FileReceivedDomainEvent;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Component
@Slf4j
public class XmlProcessor {

  /**
   * Handling of Domain Event
   *
   * @param fileReceivedDomainEvent FileReceivedDomainEvent
   */
  @Async @EventListener public void handleFileRecievedDomainEvent(FileReceivedDomainEvent fileReceivedDomainEvent) {
  //  processFile();
  }

  /**
   *
   */

  public void processFile(final long execId, final FileDto fileDto) {
    InputStream stream = new ByteArrayInputStream(Base64.decodeBase64(fileDto.getFileContent()));
    String processingMessage;
    try(Workbook workbook = new XSSFWorkbook(stream)){

      final Stream<Sheet> sheets = StreamSupport.stream(workbook.spliterator(), false);
      final long errorCount = sheets.mapToLong(sheet->processSheet()).sum();

      processingMessage = (errorCount == 0)? StringUtils.EMPTY : String.format("%d errors found on Excel file.",errorCount);

    }catch (IOException | NotOfficeXmlFileException e){
      processingMessage = MessageFormat.format("Can't open excel workbook {}",stream);
      log.error(processingMessage);
    }
  }
}
