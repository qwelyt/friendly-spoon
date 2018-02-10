package config;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class ServerFactory {
    @JsonProperty("host")
    private String host;
    @JsonProperty("port")
    private int port;

    public String host() {
        return host;
    }

    public int port() {
        return port;
    }

    @Override
    public String toString() {
        return "ServerFactory{" + "host='" + host + '\'' +
               ", port=" + port +
               '}';
    }
}
