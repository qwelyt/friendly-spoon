package queue.rabbitmq;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.channels.AlreadyConnectedException;
import java.util.concurrent.TimeoutException;

public final class RabbitMqConnectionService {
    private static Connection instance = null;

    public static Connection instance(final String host, final int port, final String user, final String password) throws IOException, TimeoutException {
        if (instance == null) {
            final ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(host);
            factory.setPort(port);
/*            factory.setUsername(user);
            factory.setPassword(password);*/
            instance = factory.newConnection();
            return instance;
        } else {
            throw new AlreadyConnectedException();
        }
    }

    public static Connection instance() {
        return instance;
    }
}
