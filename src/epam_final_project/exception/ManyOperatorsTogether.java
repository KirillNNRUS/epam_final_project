package epam_final_project.exception;

public class ManyOperatorsTogether extends Exception {
    public ManyOperatorsTogether() {
    }

    public ManyOperatorsTogether(String message) {
        super(message);
    }

    public ManyOperatorsTogether(String message, Throwable cause) {
        super(message, cause);
    }

    public ManyOperatorsTogether(Throwable cause) {
        super(cause);
    }
}
