package somesh.github.io.fileconsumer.app.service.messaging;

import org.springframework.core.env.Environment;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import somesh.github.io.fileconsumer.infra.messaging.KafkaMessagePublisher;

/**
 * FileUploadedMessagePublisher publisher.
 * 
 * @author sombose
 *
 */
@Component
public class FileStatusUpdatedMessagePublisher implements KafkaMessagePublisher<FileStatusUpdatedMessageEvent> {

  private final Environment env;
  private final KafkaTemplate<String, String> kafkaTemplate;

  /**
   * Constructor for FileUploadedMessagePublisher.
   * 
   * @param env Environment
   * @param kafkaTemplate KafkaTemplate<String,String>
   */
  public FileStatusUpdatedMessagePublisher(Environment env, KafkaTemplate<String, String> kafkaTemplate) {
    this.env = env;
    this.kafkaTemplate = kafkaTemplate;
  }

  @Override
  public String getTopic() {
    return "filestatus";
  }

  @Override
  public KafkaTemplate<String, String> getKafkaTemplate() {
    return kafkaTemplate;
  }

  /**
   * Publishes an FileStatusMessageEvent into Kafka.
   * 
   * @param msgEvent FileStatusMessageEvent
   */
  public void publish(FileStatusUpdatedMessageEvent msgEvent) {
    this.publish(msgEvent.getEventName(), msgEvent);
  }

  @Override
  public void onFailure(Throwable ex, Message<FileStatusUpdatedMessageEvent> message) {

  }
}
