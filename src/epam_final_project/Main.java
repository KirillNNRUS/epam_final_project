package epam_final_project;

import epam_final_project.console.ConsoleInput;
import epam_final_project.console.ConsoleOutput;
import epam_final_project.exception.DivisionByZeroException;
import epam_final_project.exception.IncorrectExpressionException;
import epam_final_project.exception.IncorrectParenthesisException;
import epam_final_project.exception.IncorrectRPNArrayException;
import epam_final_project.simple.SimpleStrings;
import epam_final_project.rpn.CalculateRPN;
import epam_final_project.rpn.ParseToRPN;
import epam_final_project.work.ValidateAndManipulation;

import java.util.Collections;
import java.util.List;

public class Main {
    static boolean begin = true;
    static String input = "";
    static int temp = -1;
    static List<String> list = Collections.emptyList();

    public static void main(String[] args) {
        CalculateRPN calculateRPN = new CalculateRPN();
        ConsoleInput consoleInput = new ConsoleInput();
        ConsoleOutput consoleOutput = new ConsoleOutput();
        ParseToRPN parseToRPN = new ParseToRPN();
        SimpleStrings simpleStrings = new SimpleStrings();
        ValidateAndManipulation validateAndManipulation = new ValidateAndManipulation();

        consoleOutput.consoleOutput("Данная программа может сосчитать введенное Вами математическое выражение");
        consoleOutput.consoleOutput("Хотите попробовать?");

        startOrEnd();

        while (begin) {
            consoleOutput.consoleOutput(simpleStrings.getENTER_EXPRESSION());
            consoleOutput.printAttentions();
            input = "";
            input = consoleInput.scannerInputString();

            input = validateAndManipulation.allStringManipulation(input);

            try {
                validateAndManipulation.allStringValidate(input);
                try {
                    list = parseToRPN.parseToRPN(input);
                    try {
                        calculateRPN.calculate(list);
                        //Если ошибка дальше просто не пойдет
                        list.add(simpleStrings.getRPN_ARRAY());
                        //вернем секретный ингридиент, который был убран в строке calculateRPN.calculate(list);
                        consoleOutput.consoleOutput("Ваше выражение : " + input);
                        consoleOutput.consoleOutput("Результат Вашего выражения : " + calculateRPN.calculate(list));
                    } catch (DivisionByZeroException | IncorrectRPNArrayException e) {
                        System.err.println(e);
                    } finally {
                        begin = false;
                        consoleInput.scannerClose();
                    }
                } catch (IncorrectParenthesisException | IncorrectExpressionException e) {
                    System.err.println(e);
                    consoleOutput.consoleOutput(simpleStrings.getSECOND_TRY());
                    startOrEnd();
                }
            } catch (IncorrectExpressionException | IncorrectParenthesisException e) {
                System.err.println(e);
                consoleOutput.consoleOutput(simpleStrings.getSECOND_TRY());
                startOrEnd();
            }
        }
    }

    static void startOrEnd() {
        ConsoleOutput consoleOutput = new ConsoleOutput();
        ConsoleInput consoleInput = new ConsoleInput();
        temp = -1;
        consoleOutput.printYesOrNo();
        temp = new ConsoleInput().scannerInputToInt();
        if (temp != 1) {
            consoleInput.scannerClose();
            System.exit(0);
        }
    }
}
