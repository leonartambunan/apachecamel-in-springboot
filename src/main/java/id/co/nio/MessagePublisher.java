package id.co.nio;

public interface MessagePublisher {
    void publish(final String topic, final String message);
}
