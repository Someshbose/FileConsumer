package somesh.github.io.fileconsumer.app.service.messaging;

import java.time.Instant;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import somesh.github.io.fileconsumer.app.shared.MessageEvent;
import somesh.github.io.fileconsumer.infra.messaging.InstantSerializer;

/**
 * FileUploadedMesageEvent event.
 * 
 * @author sombose
 *
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public final class FileUploadedMesageEvent implements MessageEvent {

  @Builder.Default
  private String eventName = FileUploadedMesageEvent.class.getSimpleName();

  @JsonSerialize(using = InstantSerializer.class)
  private Instant eventDate;

  private String fileLocation;

  private String fileName;

  private String fileTypeCode;

  private String uploadedBy;

}
