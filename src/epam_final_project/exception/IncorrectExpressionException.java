package epam_final_project.exception;

public class IncorrectExpressionException extends Exception{
    public IncorrectExpressionException() {
    }

    public IncorrectExpressionException(String message) {
        super(message);
    }

    public IncorrectExpressionException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectExpressionException(Throwable cause) {
        super(cause);
    }
}
