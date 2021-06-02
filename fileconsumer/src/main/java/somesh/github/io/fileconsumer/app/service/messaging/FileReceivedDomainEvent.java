package somesh.github.io.fileconsumer.app.service.messaging;

import org.springframework.context.ApplicationEvent;

/**
 *
 */
public class FileReceivedDomainEvent extends ApplicationEvent {

    private FileUploadedMesageEvent mesageEvent;

    /**
     *
     * @param source source
     * @param fileUploadedMesageEvent FileUploadedMesageEvent
     */
    public FileReceivedDomainEvent(Object source, FileUploadedMesageEvent fileUploadedMesageEvent) {
        super(source);
        this.mesageEvent = fileUploadedMesageEvent;
    }

    /**
     *
     * @return FileUploadedMesageEvent
     */
    public FileUploadedMesageEvent getMesageEvent() {
        return mesageEvent;
    }
}
