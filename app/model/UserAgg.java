package model;

import java.util.List;

public class UserAgg extends User {

    private RoundedAggregate roundedAggregate;

    public UserAgg() {
    }

    public UserAgg(String uuid, String email, String resource_uri, String resource_type, RoundedAggregate roundedAggregate) {
        super(uuid, email, resource_uri, resource_type);
        this.roundedAggregate = roundedAggregate;
    }

    public RoundedAggregate getRoundedAggregate() {
        return roundedAggregate;
    }
}
