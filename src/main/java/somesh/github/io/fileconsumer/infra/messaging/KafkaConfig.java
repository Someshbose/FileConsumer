package somesh.github.io.fileconsumer.infra.messaging;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ErrorHandler;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

/**
 * KafkaConfig class.
 * 
 * @author sombose
 */
@Configuration
@EnableKafka
public class KafkaConfig {

  private final ErrorHandler errorHandler;

  /**
   * constructor of KafkaConfig.
   * 
   * @param errorHandler ErrorHandler
   */
  public KafkaConfig(ErrorHandler errorHandler) {
    this.errorHandler = errorHandler;
  }

  /**
   * kafkaTemplate bean.
   * 
   * @param producerFactory ProducerFactory<String, String>
   * @return KafkaTemplate<String, String>
   */
  @Bean
  KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String, String> producerFactory) {
    KafkaTemplate<String, String> kafkaTemplate = new KafkaTemplate<>(producerFactory);
    kafkaTemplate.setMessageConverter(new StringJsonMessageConverter());
    return kafkaTemplate;
  }

  /**
   * Method of ListenerContainer bean.
   * 
   * @param consumerFactory ConsumerFactory<String, String>
   * @return ConcurrentKafkaListenerContainerFactory<String, String>
   */
  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(
      ConsumerFactory<String, String> consumerFactory) {
    ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory);
    factory.setErrorHandler(errorHandler);
    factory.setMessageConverter(new StringJsonMessageConverter());
    return factory;
  }
}
