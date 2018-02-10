package config;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class SystemFactory {
    @JsonProperty("name")
    private String name;
    @JsonProperty("version")
    private String version;

    public String name() {
        return name;
    }

    public String version() {
        return version;
    }

    @Override
    public String toString() {
        return "SystemFactory{" + "name='" + name + '\'' +
               ", version='" + version + '\'' +
               '}';
    }
}
