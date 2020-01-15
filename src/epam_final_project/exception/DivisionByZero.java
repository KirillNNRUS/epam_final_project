package epam_final_project.exception;

public class DivisionByZero extends Exception {
    public DivisionByZero() {
    }

    public DivisionByZero(String message) {
        super(message);
    }

    public DivisionByZero(String message, Throwable cause) {
        super(message, cause);
    }

    public DivisionByZero(Throwable cause) {
        super(cause);
    }
}
