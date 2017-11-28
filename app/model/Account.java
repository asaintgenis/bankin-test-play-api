package model;

public class Account {
    private String id;
    private String resource_uri;
    private String resource_type;

    public Account() {}

    public Account(String id, String resource_uri, String resource_type) {
        this.id = id;
        this.resource_uri = resource_uri;
        this.resource_type = resource_type;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setResource_uri(String resource_uri) {
        this.resource_uri = resource_uri;
    }

    public void setResource_type(String resource_type) {
        this.resource_type = resource_type;
    }

    public String getId() {
        return id;
    }

    public String getResource_uri() {
        return resource_uri;
    }

    public String getResource_type() {
        return resource_type;
    }
}
