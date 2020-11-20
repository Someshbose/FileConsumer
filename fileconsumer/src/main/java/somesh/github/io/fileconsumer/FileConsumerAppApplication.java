package somesh.github.io.fileconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import lombok.extern.slf4j.Slf4j;

/**
 * FileConsumer Application main class.
 * 
 * @author sombose
 */
@Slf4j
@SpringBootApplication
@SuppressWarnings("HideUtilityClassConstructor")
public class FileConsumerAppApplication {

  /**
   * main application method.
   * 
   * @param args String[]
   */
  public static void main(String[] args) {
    SpringApplication.run(FileConsumerAppApplication.class, args);
    log.info("File Consumer App is started.");
  }
}
