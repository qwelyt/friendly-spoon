package config;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class RabbitQueueFactory {
    @JsonProperty("out")
    private String out;
    @JsonProperty("in")
    private String in;

    public String out() {
        return out;
    }

    public String in() {
        return in;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RabbitQueueFactory{");
        sb.append("out='").append(out).append('\'');
        sb.append(", in='").append(in).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
