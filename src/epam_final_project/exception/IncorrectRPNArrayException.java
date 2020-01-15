package epam_final_project.exception;

public class IncorrectRPNArrayException extends Exception {
    public IncorrectRPNArrayException() {
    }

    public IncorrectRPNArrayException(String message) {
        super(message);
    }

    public IncorrectRPNArrayException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectRPNArrayException(Throwable cause) {
        super(cause);
    }
}
