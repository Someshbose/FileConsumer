package somesh.github.io.fileconsumer.app.service;

import lombok.Getter;

import java.time.Instant;

@Getter
public class FileUploadedExecCreatedDomainEvent {
    private String fileReferenceId;
    private Instant occuredOn;
    private Long execId;
}
