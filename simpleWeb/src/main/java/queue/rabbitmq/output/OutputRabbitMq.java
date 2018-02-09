package queue.rabbitmq.output;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import queue.OutputQueue;
import queue.QueuePattern;

import java.io.IOException;

public final class OutputRabbitMq implements OutputQueue {
    private final Channel channel;
    private final String queueName;
    private final QueuePattern pattern;

    public OutputRabbitMq(final Channel channel, final String queueName, final QueuePattern pattern) {
        this.channel = channel;
        this.queueName = queueName;
        this.pattern = pattern;
        switch (pattern) {
            case PUB_SUB:
                setupPubSub(channel, queueName);
                break;
        }
    }

    @Override
    public void publish(final String message) {
        try {
            channel.basicPublish(queueName, "", null, message.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setupPubSub(final Channel channel, final String queueName) {
        try {
            channel.exchangeDeclare(queueName, BuiltinExchangeType.FANOUT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
