import com.rabbitmq.client.Connection;
import config.ConfigParser;
import config.Configuration;
import config.RabbitMqFactory;
import config.RabbitQueueFactory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import queue.*;
import queue.rabbitmq.RabbitMqConnectionService;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

public final class SimpleWeb {
    public static void main(final String[] args) throws Exception {
        System.out.println("Starting the system!");
        final String collect = Arrays.stream(args).collect(Collectors.joining());
        System.out.println("Parameters: " + collect);

        final String[] split = collect.split("[\\s]");
        final Optional<String> config = Arrays.stream(split)
                                              .filter(s -> s.split("=")[0].equalsIgnoreCase("-config"))
                                              .map(s -> s.split("=")[1])
                                              .findFirst();
        if (config.isPresent()) {
            final Optional<Configuration> configuration = ConfigParser.parse(config.get());
            if (configuration.isPresent()) {
                new SimpleWeb().run(configuration.get());
            } else {
                noConfig();
            }
        } else {
            noConfig();
        }
    }

    private static void noConfig() {
        System.err.println("No config!");
        System.exit(1);
    }

    private OutputQueue outputQueue;
    private void run(final Configuration config) throws Exception {
        System.out.println("Running with config: " + config);

        setupRabbitMq(config.rabbitmq());
        final AbstractMap.SimpleImmutableEntry<InputQueue, OutputQueue> queues = startRabbitQs(config.rabbitqs());
        outputQueue = queues.getValue();

        final int port = config.server().port();
        startServer(port, resourceConfig());

        System.out.println("===== Server running on port: " + port);

    }

    private AbstractMap.SimpleImmutableEntry<InputQueue, OutputQueue> startRabbitQs(final RabbitQueueFactory rabbitqs) {
        final InputQueue inputQueue = QueueFactory.inputQueue(rabbitqs.in(), QueueType.RABBITMQ, QueuePattern.PUB_SUB, this::printer);
        final OutputQueue outputQueue = QueueFactory.outputQueue(rabbitqs.out(), QueueType.RABBITMQ, QueuePattern.PUB_SUB);
        return new AbstractMap.SimpleImmutableEntry<>(inputQueue, outputQueue);
    }

    private void printer(final String s) {
        System.out.println(s);
        outputQueue.publish(s);
    }

    private Connection setupRabbitMq(final RabbitMqFactory rabbitmq) throws IOException, TimeoutException {
        return RabbitMqConnectionService.instance(rabbitmq.host(), rabbitmq.port(), rabbitmq.user(), rabbitmq.password());
    }

    private ResourceConfig resourceConfig() {
        final ResourceConfig resourceConfig = new ResourceConfig();
        // Register resources here!
        //resourceConfig.register(new DataResource());
        return resourceConfig;
    }

    private void startServer(final int port, final ResourceConfig resourceConfig) throws Exception {
        final ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        handler.setContextPath("/");
        final Server server = new Server(port);
        server.setHandler(handler);
        handler.addServlet(new ServletHolder(new ServletContainer(resourceConfig)), "/*");
        server.start();
    }
}
