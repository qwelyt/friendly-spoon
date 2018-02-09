package config;

public final class SystemFactory {
    private String name;
    private String version;

    public String name() {
        return name;
    }

    public String version() {
        return version;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SystemFactory{");
        sb.append("name='").append(name).append('\'');
        sb.append(", version='").append(version).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
