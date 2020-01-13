package epam_final_project;


import epam_final_project.console.ConsoleInput;
import epam_final_project.console.ConsoleOutput;
import epam_final_project.exception.InvalidCharactersEnteredExceptions;
import epam_final_project.exception.ParenthesisException;
import epam_final_project.work.Arithmetic;
import epam_final_project.work.ValidateAndManipulationsString;

public class Main {
    static boolean begin = true;
    static String input = "";
    static int temp = -1;

    public static void main(String[] args) {
        Arithmetic arithmetic = new Arithmetic();
        ConsoleInput consoleInput = new ConsoleInput();
        ConsoleOutput consoleOutput = new ConsoleOutput();
        ValidateAndManipulationsString validateAndManipulationsString = new ValidateAndManipulationsString();

        System.out.println(3 - 6 * 2);

        consoleOutput.consoleOutput("Данная программа может сосчитать введенное Вами математическое выражение");
        consoleOutput.consoleOutput("Хотите попробовать?");

        startOrEnd();



        while (begin) {
            consoleOutput.consoleOutput("Введите математическое выражение");
            consoleOutput.minusDivideValue();
            input = "";
            input = consoleInput.scannerInputString();

            input = validateAndManipulationsString.removeSpaces(input);
            input = validateAndManipulationsString.replaceCommaDot(input);
            input = validateAndManipulationsString.replaceTwoDot(input);

            try {
                validateAndManipulationsString.isStringHasNoBadCharacters(input);
                validateAndManipulationsString.isParenthesisCorrect(input);
            } catch (InvalidCharactersEnteredExceptions | ParenthesisException e) {
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
