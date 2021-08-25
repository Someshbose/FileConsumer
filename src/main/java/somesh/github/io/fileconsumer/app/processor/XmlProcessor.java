package somesh.github.io.fileconsumer.app.processor;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import somesh.github.io.fileconsumer.app.service.FileReceivedDomainEvent;
import somesh.github.io.fileconsumer.app.shared.SheetHandler;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Component
@Slf4j
public class XmlProcessor {

  @Autowired
  private Collection<SheetHandler> sheetHandlers;

  /**
   * Handling of Domain Event
   *
   * @param fileReceivedDomainEvent FileReceivedDomainEvent
   */
  @Async
  @EventListener
  public void handleFileRecievedDomainEvent(FileReceivedDomainEvent fileReceivedDomainEvent) {
    log.info("Handling FileReceived Event ");
    processFile(fileReceivedDomainEvent.getExecId(),fileReceivedDomainEvent.getFile());
  }



  /**
   *
   * @param execId
   * @param fileDto
   */
  public void processFile(final long execId, final FileDto fileDto) {
    InputStream stream = new ByteArrayInputStream(Base64.decodeBase64(fileDto.getFileContent()));
    String processingMessage;
    try(Workbook workbook = new XSSFWorkbook(stream)){

      final Stream<Sheet> sheets = StreamSupport.stream(workbook.spliterator(), false);
      final long errorCount = sheets.mapToLong(sheet->processSheet(sheet,fileDto)).sum();

      processingMessage = (errorCount == 0)? StringUtils.EMPTY : String.format("%d errors found on Excel file.",errorCount);

    }catch (IOException | NotOfficeXmlFileException e){
      processingMessage = MessageFormat.format("Can't open excel workbook {}",stream);
      log.error(processingMessage);
    }

    //To-do publish FileProcessed MessageEvent
  }

  private long processSheet(final Sheet sheet, FileDto dto){
    final Optional<SheetHandler> handler = sheetHandlers.stream().filter(h-> h.canHandle(sheet)).findAny();
    long errorCount = 0L;
    try {
      if (handler.isPresent()){
        log.info("Processing Excel Sheet '{}'.",sheet.getSheetName()) ;
      } else {
        //publish SheetNameNotSupportedDomain EVent.
      }
    } catch(InvalidFileContentException e){
      errorCount = e.getMessage().split(",").length;
      log.error("{} error occuered when processing excel sheet '{}' ",errorCount,sheet.getSheetName());
    }
    return errorCount;
  }
}
