package queue.rabbitmq.input;

import com.rabbitmq.client.*;
import queue.InputQueue;
import queue.QueuePattern;

import java.io.IOException;
import java.util.function.Consumer;

public final class InputRabbitMq implements InputQueue {
    public InputRabbitMq(final Channel channel, final String queueName, final QueuePattern queuePattern, final Consumer<String> consumer) {
        switch (queuePattern) {
            case PUB_SUB:
                listenPubSub(channel, queueName, consumer);
                break;
        }
    }

    private void listenPubSub(final Channel channel, final String queueName, final Consumer<String> handler) {
        try {
            channel.exchangeDeclare(queueName, BuiltinExchangeType.FANOUT);
            final String queue = channel.queueDeclare().getQueue();
            channel.queueBind(queue, queueName, "");
            final DefaultConsumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(final String consumerTag, final Envelope envelope, final AMQP.BasicProperties properties, final byte[] body) throws IOException {
                    final String message = new String(body, "UTF-8");
                    handler.accept(message);
                }
            };
            channel.basicConsume(queueName, true, consumer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
