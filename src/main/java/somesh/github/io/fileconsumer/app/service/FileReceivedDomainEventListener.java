package somesh.github.io.fileconsumer.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import somesh.github.io.fileconsumer.app.service.messaging.FileStatusUpdatedMessageEvent;
import somesh.github.io.fileconsumer.app.service.messaging.FileStatusUpdatedMessagePublisher;
import somesh.github.io.fileconsumer.app.service.messaging.FileUploadedMessageEvent;
import somesh.github.io.fileconsumer.domain.model.FileUploadedStatus;

@Component
public class FileReceivedDomainEventListener {

    @Autowired
    private FileStatusUpdatedMessagePublisher fileStatusMessagePublisher;

    /**
     *
     * @param domainEvent FileReceivedDomainEvent
     */
    @Async
    @EventListener
    public void doPublishOnKafka(FileReceivedDomainEvent domainEvent) {
        FileStatusUpdatedMessageEvent messageEvent = createFileStatusMessageEvent(domainEvent.getMesageEvent());
        fileStatusMessagePublisher.publish(messageEvent);
    }

    /**
     *
     * @param event FileUploadedMesageEvent
     * @return FileStatusMessageEvent
     */
    private FileStatusUpdatedMessageEvent createFileStatusMessageEvent(FileUploadedMessageEvent event) {
        return FileStatusUpdatedMessageEvent.builder().eventDate(event.getEventDate())
                .fileName(event.getFileName()).fileLocation(event.getFileLocation())
                .fileTypeCode(event.getFileTypeCode()).status(FileUploadedStatus.IN_PROGRESS).build();
    }
}
