package epam_final_project.work;

import epam_final_project.exception.InvalidCharactersEnteredExceptions;
import epam_final_project.exception.ParenthesisException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateAndManipulationsString {

    private String notValidRegExpString = "[^\\d^/\\-() .,*+]+";
    //По моему мнению, это выражение все кроме Цифр и -() .,*+
    //Я далее , заменяю на .
    //Так и не понял, как написать \ что бы потом заменить на /
    private final String spaces = "\\s+";
    private final String NoSpace = "";
    private final String comma = ",";
    private final String dot = ".";
    private final String twoDot = "..";

    public String removeSpaces(String value) {
        return value.replaceAll(spaces, NoSpace);
    }

    public String replaceCommaDot(String value) {
        return value.replaceAll(comma, dot);
    }

    public String replaceTwoDot(String value) {
        return value.replaceAll(twoDot, dot);
    }

    public void isStringHasNoBadCharacters(String value) throws InvalidCharactersEnteredExceptions {
        //Проверяем нет ли букв и т.д.
        Pattern pattern = Pattern.compile(notValidRegExpString);
        Matcher matcher = pattern.matcher(value);
        if (matcher.find()) {
            throw new InvalidCharactersEnteredExceptions("Введены некорректные символы, например буквы.");
        }
    }

    public void isParenthesisCorrect(String value) throws ParenthesisException {
        int beginParenthesis = 0;
        int endParenthesis = 0;
        for (int i = 0; i < value.length(); i++) {
            if ((String.valueOf(value.charAt(i))).equals("(")) {
                beginParenthesis++;
            }
            if ((String.valueOf(value.charAt(i))).equals(")")) {
                endParenthesis++;
            }
        }
        if (beginParenthesis != endParenthesis) {
            throw new ParenthesisException("Количество открывающих скобок, не равно количеству закрывающих.");
        }
    }
}
