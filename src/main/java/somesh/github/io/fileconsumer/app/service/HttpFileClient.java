package somesh.github.io.fileconsumer.app.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.web.client.RestTemplate;
import somesh.github.io.fileconsumer.app.processor.FileDto;
import somesh.github.io.fileconsumer.app.service.messaging.FileUploadedMessageEvent;

import java.util.Optional;

@Component
@Slf4j
public class HttpFileClient {


  @Autowired
  private RestTemplate restTemplate;

  private final String URL = "http://localhost:8080/app/service/filestore/";

  @TransactionalEventListener
  public void handleFileUploadedExecCreatedEvent(FileUploadedExecCreatedDomainEvent event){
    log.info("Fetching file.");
    Optional<FileDto> file = Optional.ofNullable(fetchFile(event));

    if (file.isPresent()){
      log.info("File Received domain event triggered");
      //publish FileReceivedDomainEvent
    }else{
      log.error("Unable to get File");
      //publish FileNotReceivedDomainEvent
    }
  }


  private FileDto fetchFile(FileUploadedExecCreatedDomainEvent domainEvent){

    String fileUrl = URL+ domainEvent.getFileReferenceId();
    ResponseEntity<FileDto> response = restTemplate.exchange(fileUrl, HttpMethod.GET,null,FileDto.class);

    return response.getBody();
  }
}
