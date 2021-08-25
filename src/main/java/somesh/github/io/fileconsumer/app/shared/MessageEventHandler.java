package somesh.github.io.fileconsumer.app.shared;

public interface MessageEventHandler<T extends MessageEvent> {

  boolean canHandle(T event);

  void handleEvent(T event);
}
