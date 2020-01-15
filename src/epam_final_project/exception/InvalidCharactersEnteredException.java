package epam_final_project.exception;

public class InvalidCharactersEnteredException extends Exception {
    public InvalidCharactersEnteredException() {
    }

    public InvalidCharactersEnteredException(String message) {
        super(message);
    }

    public InvalidCharactersEnteredException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidCharactersEnteredException(Throwable cause) {
        super(cause);
    }
}
