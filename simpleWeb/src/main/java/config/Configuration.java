package config;

public final class Configuration {
    private SystemFactory system;
    private ServerFactory server;
    private RabbitMqFactory rabbitmq;
    private RabbitQueueFactory rabbitqs;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Configuration{");
        sb.append("system=").append(system);
        sb.append(", server=").append(server);
        sb.append(", rabbitmq=").append(rabbitmq);
        sb.append(", rabbitqs=").append(rabbitqs);
        sb.append('}');
        return sb.toString();
    }
}
