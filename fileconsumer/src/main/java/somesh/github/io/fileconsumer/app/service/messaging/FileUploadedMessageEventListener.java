package somesh.github.io.fileconsumer.app.service.messaging;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

/**
 * FileStatusMessageEvent Consumer class.
 * 
 * @author sombose
 */
@Slf4j
@Component
public class FileUploadedMessageEventListener {
  /**
   * method to read message.
   * 
   * @param messageEvent FileUploadedMesageEvent
   */
  @KafkaListener(topics = "filestatus", id = "fileStatusConsumer")
  public void consumeMessage(FileUploadedMesageEvent messageEvent) {
    log.info("Message Consumed is {}", messageEvent);
  }
}
