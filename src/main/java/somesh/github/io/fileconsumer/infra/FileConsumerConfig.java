package somesh.github.io.fileconsumer.infra;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class FileConsumerConfig {

  @Bean
  public RestTemplate getRestTemplate(){
    return new RestTemplate();
  }

}
