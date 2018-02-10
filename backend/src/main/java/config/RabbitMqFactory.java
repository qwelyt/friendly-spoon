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
    @JsonProperty("virtualHost")
    private String virtualHost;

    @JsonProperty("useSsl")
    private boolean useSsl;
    @JsonProperty("ssl")
    private RabbitMqSSLFactory ssl;

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

    public String virtualHost() {
        return virtualHost;
    }

    public boolean useSsl() {
        return useSsl;
    }

    public RabbitMqSSLFactory ssl() {
        return ssl;
    }

    @Override
    public String toString() {
        return "RabbitMqFactory{" + "host='" + host + '\'' +
               ", port=" + port +
               ", user='" + user + '\'' +
               ", password='" + password + '\'' +
               ", virtualHost='" + virtualHost + '\'' +
               ", useSsl=" + useSsl +
               ", ssl=" + ssl +
               '}';
    }
}
