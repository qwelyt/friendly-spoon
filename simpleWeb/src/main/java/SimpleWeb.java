import config.ConfigParser;
import config.Configuration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import java.util.Arrays;
import java.util.Optional;
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

    private void run(final Configuration config) throws Exception {
        System.out.println("Running with config: " + config);

        final int port = 8989;
        startServer(port, resourceConfig());

        System.out.println("===== Server running on port: " + port);

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
