package somesh.github.io.fileconsumer.app.service.messaging;

import org.springframework.core.env.Environment;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import somesh.github.io.fileconsumer.infra.messaging.KafkaMessagePublisher;

/**
 * FileUploadedMessagePublisher publisher.
 * 
 * @author sombose
 *
 */
@Component
public class FileStatusMessagePublisher implements KafkaMessagePublisher<FileStatusMessageEvent> {

  private final Environment env;
  private final KafkaTemplate<String, String> kafkaTemplate;

  /**
   * Constructor for FileUploadedMessagePublisher.
   * 
   * @param env Environment
   * @param kafkaTemplate KafkaTemplate<String,String>
   */
  public FileStatusMessagePublisher(Environment env, KafkaTemplate<String, String> kafkaTemplate) {
    this.env = env;
    this.kafkaTemplate = kafkaTemplate;
  }

  @Override
  public String getTopic() {
    return env.getProperty("kafka.topic-name");
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
  public void publish(FileStatusMessageEvent msgEvent) {
    this.publish(msgEvent.getEventName(), msgEvent);
  }

}
