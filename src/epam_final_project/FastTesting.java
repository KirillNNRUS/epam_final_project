package epam_final_project;

import epam_final_project.exception.DivisionByZeroException;
import epam_final_project.exception.IncorrectExpressionException;
import epam_final_project.exception.IncorrectParenthesisException;
import epam_final_project.exception.IncorrectRPNArrayException;
import epam_final_project.rpn.CalculateRPN;
import epam_final_project.rpn.ParseToRPN;
import epam_final_project.prepare.Manipulation;
import epam_final_project.prepare.Validate;

import java.util.ArrayList;
import java.util.List;

public class FastTesting {
    public static void main(String[] args) {
        CalculateRPN calculateRPN = new CalculateRPN();
        Manipulation manipulation = new Manipulation();
        ParseToRPN parseToRPN = new ParseToRPN();
        Validate validate = new Validate();
        List<String> list = new ArrayList<>();

        //String s = " 16*(50,9^4*(3,69))-(6/2,1+10,09^(-3))*3/6,3";
        String s = " 34+5,3+65-1*3*639,3-(6)";

        s = manipulation.allStringManipulation(s);
        System.out.println(s);
        try {
            validate.allStringValidate(s);
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
