package epam_final_project.exception;

public class DivisionByZeroException extends Exception {
    public DivisionByZeroException() {
    }

    public DivisionByZeroException(String message) {
        super(message);
    }

    public DivisionByZeroException(String message, Throwable cause) {
        super(message, cause);
    }

    public DivisionByZeroException(Throwable cause) {
        super(cause);
    }
}
