package config;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class RabbitMqSSLFactory {
    @JsonProperty("keyPassword")
    private String keyPassword;
    @JsonProperty("certFormat")
    private String certFormat;
    @JsonProperty("pathToCert")
    private String pathToCert;
    @JsonProperty("rabbitTrustStorePassword")
    private String rabbitTrustStorePassword;
    @JsonProperty("pathToRabbitTrustStore")
    private String pathToRabbitTrustStore;
    @JsonProperty("tlsVersion")
    private String tlsVersion;

    public String keyPassword() {
        return keyPassword;
    }

    public String certFormat() {
        return certFormat;
    }

    public String pathToCert() {
        return pathToCert;
    }

    public String rabbitTrustStorePassword() {
        return rabbitTrustStorePassword;
    }

    public String pathToRabbitTrustStore() {
        return pathToRabbitTrustStore;
    }

    public String tlsVersion() {
        return tlsVersion;
    }

    @Override
    public String toString() {
        return "RabbitMqSSLFactory{" + "keyPassword='" + keyPassword + '\'' +
               ", certFormat='" + certFormat + '\'' +
               ", pathToCert='" + pathToCert + '\'' +
               ", rabbitTrustStorePassword='" + rabbitTrustStorePassword + '\'' +
               ", pathToRabbitTrustStore='" + pathToRabbitTrustStore + '\'' +
               ", tlsVersion='" + tlsVersion + '\'' +
               '}';
    }
}
