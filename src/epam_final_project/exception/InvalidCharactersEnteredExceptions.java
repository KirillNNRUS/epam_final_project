package epam_final_project.exception;

public class InvalidCharactersEnteredExceptions extends Exception {
    public InvalidCharactersEnteredExceptions() {
    }

    public InvalidCharactersEnteredExceptions(String message) {
        super(message);
    }

    public InvalidCharactersEnteredExceptions(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidCharactersEnteredExceptions(Throwable cause) {
        super(cause);
    }
}
