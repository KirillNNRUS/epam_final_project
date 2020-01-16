package epam_final_project.exception;

public class IncorrectParenthesisException extends Exception{
    public IncorrectParenthesisException() {
    }

    public IncorrectParenthesisException(String message) {
        super(message);
    }

    public IncorrectParenthesisException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectParenthesisException(Throwable cause) {
        super(cause);
    }
}
