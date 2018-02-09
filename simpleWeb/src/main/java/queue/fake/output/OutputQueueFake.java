package queue.fake.output;

import queue.OutputQueue;

import java.util.ArrayList;
import java.util.List;

public final class OutputQueueFake implements OutputQueue {
    private final List<String> published;
    private final String queueName;

    public OutputQueueFake(final String queueName) {
        this.queueName = queueName;
        this.published = new ArrayList<>();
    }

    @Override
    public void publish(final String message) {
        published.add(message);
    }

    public String queueName() {
        return queueName;
    }

    public List<String> published() {
        return new ArrayList<>(published);
    }
}
