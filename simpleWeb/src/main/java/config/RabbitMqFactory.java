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
        final StringBuilder sb = new StringBuilder("RabbitMqFactory{");
        sb.append("host='").append(host).append('\'');
        sb.append(", port=").append(port);
        sb.append(", user='").append(user).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
