package epam_final_project;

import epam_final_project.console.ConsoleInput;
import epam_final_project.console.ConsoleOutput;
import epam_final_project.exception.IncorrectExpressionException;
import epam_final_project.exception.ParenthesisException;
import epam_final_project.rpn.ValidateAndManipulation;

public class Main {
    static boolean begin = true;
    static String input = "";
    static int temp = -1;

    public static void main(String[] args) {
        ConsoleInput consoleInput = new ConsoleInput();
        ConsoleOutput consoleOutput = new ConsoleOutput();
        ValidateAndManipulation validateAndManipulation = new ValidateAndManipulation();

        consoleOutput.consoleOutput("Данная программа может сосчитать введенное Вами математическое выражение");
        consoleOutput.consoleOutput("Хотите попробовать?");

        startOrEnd();

        while (begin) {
            consoleOutput.consoleOutput("Введите математическое выражение");
            consoleOutput.printAttentions();
            input = "";
            input = consoleInput.scannerInputString();

            input = validateAndManipulation.allStringManipulation(input);

            try {
                validateAndManipulation.allStringValidate(input);
            } catch (IncorrectExpressionException | ParenthesisException e) {
                System.err.println(e);
                consoleOutput.consoleOutput("Хотите попробовать еще раз?");
                startOrEnd();
            }

            System.out.println(input);

        }
    }

    static void yesOrNoConsolePrint() {
        ConsoleOutput consoleOutput = new ConsoleOutput();
        consoleOutput.consoleOutput("1 - ДА, все остальное НЕТ");
    }

    static void startOrEnd() {
        temp = -1;
        ConsoleInput consoleInput = new ConsoleInput();
        yesOrNoConsolePrint();
        temp = new ConsoleInput().scannerInputToInt();
        if (temp != 1) {
            consoleInput.scannerClose();
            System.exit(0);
        }
    }
}
