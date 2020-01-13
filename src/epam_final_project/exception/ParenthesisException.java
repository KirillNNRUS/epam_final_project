package epam_final_project.exception;

public class ParenthesisException extends Exception{
    public ParenthesisException() {
    }

    public ParenthesisException(String message) {
        super(message);
    }

    public ParenthesisException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParenthesisException(Throwable cause) {
        super(cause);
    }
}
