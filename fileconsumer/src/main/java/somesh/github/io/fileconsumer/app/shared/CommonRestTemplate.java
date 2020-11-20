package somesh.github.io.fileconsumer.app.shared;

import java.util.Arrays;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public interface CommonRestTemplate {

  RestTemplate getrestTemplate();

  default void restCall() {
    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    HttpEntity<String> entity = new HttpEntity<String>(headers);

    getrestTemplate().exchange("http://localhost:8080/products", HttpMethod.GET, entity, String.class).getBody();
  }
}
