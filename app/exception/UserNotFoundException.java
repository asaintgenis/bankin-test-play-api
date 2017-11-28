package exception;

public class UserNotFoundException extends Exception {
    String mail;

    public UserNotFoundException(String mail) {
        this.mail = mail;
    }

    public String getMail() {
        return mail;
    }
}
