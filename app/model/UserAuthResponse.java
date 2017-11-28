package model;

public class UserAuthResponse {
    private User user;
    private String access_token;
    private String expires_at;

    public UserAuthResponse() {
    }

    public UserAuthResponse(User user, String access_token, String expires_at) {
        this.user = user;
        this.access_token = access_token;
        this.expires_at = expires_at;
    }

    public User getUser() {
        return user;
    }

    public String getAccess_token() {
        return access_token;
    }

    public String getExpires_at() {
        return expires_at;
    }
}
