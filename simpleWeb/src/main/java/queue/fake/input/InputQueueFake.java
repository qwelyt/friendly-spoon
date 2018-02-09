package queue.fake.input;

import queue.InputQueue;

import java.util.function.Consumer;

public final class InputQueueFake implements InputQueue {
    private final String queueName;
    private final Consumer<String> consumer;

    public InputQueueFake(final String queueName, final Consumer<String> consumer) {
        this.queueName = queueName;
        this.consumer = consumer;
    }

    public String queueName() {
        return queueName;
    }

    public void sendInput(final String message) {
        consumer.accept(message);
    }
}
