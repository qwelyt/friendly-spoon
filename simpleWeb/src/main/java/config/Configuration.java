package config;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class Configuration {
    @JsonProperty("system")
    private SystemFactory system;
    @JsonProperty("server")
    private ServerFactory server;
    @JsonProperty("rabbitmq")
    private RabbitMqFactory rabbitmq;
    @JsonProperty("rabbitqs")
    private RabbitQueueFactory rabbitqs;

    public SystemFactory system() {
        return system;
    }

    public ServerFactory server() {
        return server;
    }

    public RabbitMqFactory rabbitmq() {
        return rabbitmq;
    }

    public RabbitQueueFactory rabbitqs() {
        return rabbitqs;
    }

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
