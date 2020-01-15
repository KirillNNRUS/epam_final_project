package epam_final_project.rpn;

import epam_final_project.exception.InvalidCharactersEnteredException;
import epam_final_project.exception.ParenthesisException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateAndManipulation {

    private String notValidRegExpString = "[^\\d ()+\\-.,*/^]+";
    //По моему мнению, это выражение - Все кроме Цифр, пробела, скобок,
    // плюс, минус, точка, запятая, звездочка, slash, circumflexus (^)
    //Так и не понял, как написать backslash (включить в RegExp символ backslash),
    // чтобы потом заменить на slash, подскажите пожалуйста :-)
    private final String SPACES = "\\s+";
    private final String NO_SPACE = "";
    private final String COMMA = ",";
    private final String DOT = ".";
    private final String TWO_DOT = "[.]{2}";
    private final String INCORRECT_PARENTHESES_VERSION_ONE = "[(][\\d]+[.][\\d]+[)][\\d]";
    //Для 589..665656
    private final String TWO_DOT_IN_ONE_CODE_VERSION_ONE = "[\\d]+[.][\\d]+[.]";
    //Для 589.665656.
    private final String TWO_DOT_IN_ONE_CODE_VERSION_TWO = "[.][\\d]+[.][\\d]+";
    //Для .589.665656
    //Хоть IDEA и предлагает сделать их все локальными, оставлю,
    // мне так больше нравиться, когда все в одном месте

    public String allStringManipulation(String value) {
        value = removeSpaces(value);
        value = replaceCommaDot(value);
        System.out.println("Выражение после \"уборки\" " + value);
        return value;
    }

    private String removeSpaces(String value) {
        //Удаляю пробелы
        return value.replaceAll(SPACES, NO_SPACE);
    }

    private String replaceCommaDot(String value) {
        //Делаю из запятой точку
        return value.replaceAll(COMMA, DOT);
    }

    public void allStringValidate(String value)
            throws InvalidCharactersEnteredException, ParenthesisException {
        isStringHasNoBadCharacters(value);
        isStringHasTwoDot(value);
        isParenthesisCorrect(value);
    }

    public void isStringHasTwoDotInOneCode(String value)
            throws InvalidCharactersEnteredException {
        //А вдруг две точки типов 589.665656. или .589.665656 ?!

        Pattern pattern = Pattern.compile(TWO_DOT_IN_ONE_CODE_VERSION_ONE);
        Matcher matcher = pattern.matcher(value);

        if (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            throw new InvalidCharactersEnteredException(
                    "Некорректная расстановка точек " + value.substring(start, end));
        }

        pattern = Pattern.compile(TWO_DOT_IN_ONE_CODE_VERSION_TWO);
        matcher = pattern.matcher(value);

        if (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            throw new InvalidCharactersEnteredException(
                    "Некорректная расстановка точек " + value.substring(start, end));
        }
    }

    private void isStringHasTwoDot(String value)
            throws InvalidCharactersEnteredException {
        //А вдруг две точки 589..665656 ?!

        Pattern pattern = Pattern.compile(TWO_DOT);
        Matcher matcher = pattern.matcher(value);

        if (matcher.find()) {
            throw new InvalidCharactersEnteredException(
                    "Выявлено две или более точки подряд");
        }
    }

    private void isStringHasNoBadCharacters(String value)
            throws InvalidCharactersEnteredException {
        //Проверяем нет ли букв и т.д.

        Pattern pattern = Pattern.compile(notValidRegExpString);
        Matcher matcher = pattern.matcher(value);

        if (matcher.find()) {
            throw new InvalidCharactersEnteredException(
                    "Выявлены некорректные символы, например буквы.");
        }
    }

    private void isParenthesisCorrect(String value)
            throws ParenthesisException {
        //Количество "(" должно быть равно количеству ")".
        //Простейшая проверка вот такое ")(" не поймает

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
            throw new ParenthesisException(
                    "Количество открывающих скобок, не равно количеству закрывающих.");
        }
    }
}
