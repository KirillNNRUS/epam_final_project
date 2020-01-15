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

        String s = "7,6(5)+ ((5,9 + 0,3) - (-7,66 + -3,4))";
        //TODO может удастся придумать, что делать с этим?!
        s = validateAndManipulation.allStringManipulation(s);
        System.out.println(s);
        try {
            validateAndManipulation.allStringValidate(s);
            validateAndManipulation.isStringHasTwoDotInOneCode(s);
        } catch (ParenthesisException | InvalidCharactersEnteredException e) {
            System.out.println(e);
        }

        try {
            System.out.println(parseToRPN.parseToRPN(s));
            list = parseToRPN.parseToRPN(s);
        } catch (ParenthesisException | IncorrectExpressionException e) {
            System.out.println(e);
        }



        try {
            System.out.println(calculateRPN.calculate(list));
        } catch (DivisionByZeroException | IncorrectRPNArrayException e) {
            System.out.println(e);
        }

    }
}
