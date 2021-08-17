package somesh.github.io.fileconsumer.app.service.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import somesh.github.io.fileconsumer.domain.model.FileUploadedStatus;

@Component
public class FileReceivedDomainEventListener {

    @Autowired
    private FileStatusMessagePublisher fileStatusMessagePublisher;

    /**
     *
     * @param domainEvent FileReceivedDomainEvent
     */
    @Async
    @EventListener
    public void doPublishOnKafka(FileReceivedDomainEvent domainEvent) {
        FileStatusMessageEvent messageEvent = createFileStatusMessageEvent(domainEvent.getMesageEvent());
        fileStatusMessagePublisher.publish(messageEvent);
    }

    /**
     *
     * @param event FileUploadedMesageEvent
     * @return FileStatusMessageEvent
     */
    private FileStatusMessageEvent createFileStatusMessageEvent(FileUploadedMesageEvent event) {
        return FileStatusMessageEvent.builder().eventDate(event.getEventDate())
                .fileName(event.getFileName()).fileLocation(event.getFileLocation())
                .fileTypeCode(event.getFileTypeCode()).status(FileUploadedStatus.IN_PROGRESS).build();
    }
}
