package epam_final_project;

import epam_final_project.exception.*;
import epam_final_project.rpn.CalculateRPN;
import epam_final_project.rpn.ParseToRPN;
import epam_final_project.rpn.ValidateAndManipulation;

import java.util.ArrayList;
import java.util.List;

public class SecMain {
    public static void main(String[] args) {
        ValidateAndManipulation validateAndManipulation = new ValidateAndManipulation();
        ParseToRPN parseToRPN = new ParseToRPN();
        CalculateRPN calculateRPN = new CalculateRPN();
        List<String> list = new ArrayList<>();

        String s = "(7,6+ ((5,9 + 0,3))*(-5+7) - 8 / 6 -  (36)*3 - (-7,66 + -3,4))";

        s = validateAndManipulation.allStringManipulation(s);
        System.out.println(s);
        try {
            validateAndManipulation.allStringValidate(s);
            validateAndManipulation.stringHasTwoDotInOneCode(s);
        } catch (ParenthesisException | IncorrectExpressionException e) {
            System.err.println(e);
            end(777);
        }

        try {
            System.out.println(parseToRPN.parseToRPN(s));
            list = parseToRPN.parseToRPN(s);
        } catch (ParenthesisException | IncorrectExpressionException e) {
            System.err.println(e);
            end(777);
        }

        try {
            System.out.println(calculateRPN.calculate(list));
        } catch (DivisionByZeroException | IncorrectRPNArrayException e) {
            System.err.println(e);
            end(777);
        }

    }

    public static void end(int status) {
        System.exit(status);
    }
}
