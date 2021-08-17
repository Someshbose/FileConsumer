package somesh.github.io.fileconsumer.app.service.processor;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import somesh.github.io.fileconsumer.app.service.messaging.FileReceivedDomainEvent;

@Component
public class XmlProcessor {

    @Async
    @EventListener
    public void handleFileRecievedDomainEvent(FileReceivedDomainEvent fileReceivedDomainEvent){
        processFile();
    }

    public void processFile(){

    }
}
