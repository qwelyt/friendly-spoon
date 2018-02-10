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
        final StringBuilder sb = new StringBuilder("ServerFactory{");
        sb.append("host='").append(host).append('\'');
        sb.append(", port=").append(port);
        sb.append('}');
        return sb.toString();
    }
}
