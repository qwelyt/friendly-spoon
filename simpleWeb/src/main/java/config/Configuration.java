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
        return "Configuration{" + "system=" + system +
               ", server=" + server +
               ", rabbitmq=" + rabbitmq +
               ", rabbitqs=" + rabbitqs +
               '}';
    }
}
