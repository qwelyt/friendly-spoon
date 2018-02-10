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
        return "RabbitQueueFactory{" + "out='" + out + '\'' +
               ", in='" + in + '\'' +
               '}';
    }
}
