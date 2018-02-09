package config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public final class ConfigParser {
    public static Optional<Configuration> parse(final String path) {
        final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            return Optional.ofNullable(mapper.readValue(new File(path), Configuration.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
