package epam_final_project;

import epam_final_project.exception.InvalidCharactersEnteredExceptions;
import epam_final_project.exception.ParenthesisException;
import epam_final_project.rpn.ValidateAndManipulations;

public class SecMain {
    public static void main(String[] args) {
        ValidateAndManipulations validateAndManipulations = new ValidateAndManipulations();
        String s = "()589((+) , 665656)     455656 9+    6.3";
        s = validateAndManipulations.allStringManipulation(s);
        System.out.println(s);
        try {
            validateAndManipulations.allStringValidate(s);
            validateAndManipulations.isStringHasTwoDotInOneCode(s);
        } catch (ParenthesisException | InvalidCharactersEnteredExceptions e) {
            System.out.println(e);
        }

    }
}
