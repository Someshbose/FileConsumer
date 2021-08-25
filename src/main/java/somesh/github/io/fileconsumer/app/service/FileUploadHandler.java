package somesh.github.io.fileconsumer.app.service;

import org.springframework.stereotype.Component;
import somesh.github.io.fileconsumer.app.service.messaging.FileUploadedMessageEvent;
import somesh.github.io.fileconsumer.app.shared.MessageEventHandler;

@Component
public class FileUploadHandler implements MessageEventHandler<FileUploadedMessageEvent> {

  @Override public boolean canHandle(FileUploadedMessageEvent event) {
    return false;
  }

  @Override public void handleEvent(FileUploadedMessageEvent event) {
    //call ExecutionService execute()
  }
}
