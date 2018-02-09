package queue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import queue.fake.input.InputQueueFake;
import queue.fake.output.OutputQueueFake;
import queue.rabbitmq.RabbitMqConnectionService;
import queue.rabbitmq.input.InputRabbitMq;
import queue.rabbitmq.output.OutputRabbitMq;

import java.io.IOException;
import java.util.function.Consumer;

public final class QueueFactory {

    public static OutputQueue outputQueue(final String queueName, final QueueType queueType, final QueuePattern queuePattern) {
        switch (queueType) {
            case FAKE:
                return new OutputQueueFake(queueName);
            case RABBITMQ:
                return new OutputRabbitMq(channel(), queueName, queuePattern);
            default:
                throw new IllegalArgumentException("Unknown queue type");
        }
    }

    public static InputQueue inputQueue(final String queueName, final QueueType queueType, final QueuePattern queuePattern, final Consumer<String> consumer) {
        switch (queueType) {
            case FAKE:
                return new InputQueueFake(queueName, consumer);
            case RABBITMQ:
                return new InputRabbitMq(channel(), queueName, queuePattern, consumer);
            default:
                throw new IllegalArgumentException("Unknown queue type");
        }

    }

    private static Channel channel() {
        try {
            return connection().createChannel();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Connection connection() {
        final Connection instance = RabbitMqConnectionService.instance();
        return instance;
    }

}
