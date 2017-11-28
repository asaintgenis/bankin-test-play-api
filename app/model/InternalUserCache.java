package model;

import java.util.List;

public class InternalUserCache {

    private List<InternalUser> resources;

    public InternalUserCache() {
    }

    public InternalUserCache(List<InternalUser> resources) {
        this.resources = resources;
    }

    public List<InternalUser> getResources() {
        return resources;
    }

    public void setResources(List<InternalUser> resources) {
        this.resources = resources;
    }
}
