package config;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class RabbitMqFactory {
    @JsonProperty("host")
    private String host;
    @JsonProperty("port")
    private int port;
    @JsonProperty("user")
    private String user;
    @JsonProperty("password")
    private String password;

    public String host() {
        return host;
    }

    public int port() {
        return port;
    }

    public String user() {
        return user;
    }

    public String password() {
        return password;
    }

    @Override
    public String toString() {
        return "RabbitMqFactory{" + "host='" + host + '\'' +
               ", port=" + port +
               ", user='" + user + '\'' +
               ", password='" + password + '\'' +
               '}';
    }
}
