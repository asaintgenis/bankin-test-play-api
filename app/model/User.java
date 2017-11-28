package model;

public class User {
    private String uuid;
    private String email;
    private String resource_uri;
    private String resource_type;

    public User() {
    }

    public User(String uuid, String email, String resource_uri, String resource_type) {
        this.uuid = uuid;
        this.email = email;
        this.resource_uri = resource_uri;
        this.resource_type = resource_type;
    }

    public String getUuid() {
        return uuid;
    }

    public String getEmail() {
        return email;
    }

    public String getResource_uri() {
        return resource_uri;
    }

    public String getResource_type() {
        return resource_type;
    }
}
