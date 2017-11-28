package exception;

public class NotFoundResponse extends ExceptionResponse {
    String mail;

    public NotFoundResponse(String message, String mail) {
        super(message);
        this.mail = mail;
    }

    public String getMail() {
        return mail;
    }
}
