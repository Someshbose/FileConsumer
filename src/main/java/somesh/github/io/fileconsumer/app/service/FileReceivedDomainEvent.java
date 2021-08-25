package somesh.github.io.fileconsumer.app.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import somesh.github.io.fileconsumer.app.processor.FileDto;
import somesh.github.io.fileconsumer.app.service.messaging.FileUploadedMessageEvent;

import java.time.Instant;

/**
 *
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FileReceivedDomainEvent extends ApplicationEvent {

    private long execId;
    private FileDto file;
    private String fileRefId;
    private Instant occuredOn = Instant.now();


    public FileReceivedDomainEvent(Object source) {
        super(source);
    }

}
