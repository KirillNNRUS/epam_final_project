package epam_final_project;

import epam_final_project.exception.*;
import epam_final_project.rpn.CalculateRPN;
import epam_final_project.rpn.ParseToRPN;
import epam_final_project.rpn.ValidateAndManipulation;

import java.util.ArrayList;
import java.util.List;

public class FastTesting {
    public static void main(String[] args) {
        ValidateAndManipulation validateAndManipulation = new ValidateAndManipulation();
        ParseToRPN parseToRPN = new ParseToRPN();
        CalculateRPN calculateRPN = new CalculateRPN();
        List<String> list = new ArrayList<>();

//        String s = " 16*(50,9^4*(3,69))-(6/2,1+10,09^(-3))*3/6.3";
        String s = " )5.3+65(639.3-6";

        s = validateAndManipulation.allStringManipulation(s);
        System.out.println(s);
        try {
            validateAndManipulation.allStringValidate(s);
            validateAndManipulation.stringHasTwoDotInOneCode(s);
        } catch (IncorrectParenthesisException | IncorrectExpressionException e) {
            System.err.println(e);
            end(777);
        }

        try {
            System.out.println(parseToRPN.parseToRPN(s));
            list = parseToRPN.parseToRPN(s);
        } catch (IncorrectParenthesisException | IncorrectExpressionException e) {
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
