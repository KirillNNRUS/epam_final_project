package epam_final_project.rpn;

import epam_final_project.exception.IncorrectExpressionException;
import epam_final_project.exception.IncorrectParenthesisException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateAndManipulation {
    private final String INCORRECT_PARENTHESES_STRING = "Некорректная расстановка скобок";
    private final String INCORRECT_DOT = "Некорректная расстановка точек";
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
    //Для (25,5)3
    private final String INCORRECT_PARENTHESES_VERSION_TWO = "[(][\\d]+[)][\\d]";
    //Для (25)3
    private final String INCORRECT_PARENTHESES_VERSION_THREE = "[\\d][(][\\d]+[.][\\d]+[)]";
    //Для 3(25,5)
    private final String INCORRECT_PARENTHESES_VERSION_FOUR = "[\\d][(][\\d]+[)]";
    //Для 3(25,5)
    private final String INCORRECT_PARENTHESES_VERSION_FIVE = "[(][)]";
    //Для ()
    private final String INCORRECT_PARENTHESES_VERSION_SIX = "[)][(]";
    //Для )(
    private final String INCORRECT_PARENTHESES_VERSION_SEVEN = "[)][\\d]+[(]";
    //Для )5(
    private final String INCORRECT_PARENTHESES_VERSION_EIGHT = "[)][\\d]+[.][\\d]+[(]";
    //Для )5.5(
    private final String TWO_DOT_IN_ONE_CODE_VERSION_ONE = "[\\d]+[.][\\d]+[.]";
    //Для 589.665656.
    private final String TWO_DOT_IN_ONE_CODE_VERSION_TWO = "[.][\\d]+[.][\\d]+";
    //Для .589.665656
    //Хоть IDEA и предлагает сделать их все локальными, оставлю,
    // мне так больше нравиться, когда все в одном месте
    //А как все таки правильнее? локальные переменные или все в одном месте?

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
            throws IncorrectParenthesisException, IncorrectExpressionException {
        stringHasBadCharacters(value);
        stringHasTwoDot(value);
        parenthesisIncorrect(value);
        incorrectParentheses(value);
    }

    public void incorrectParentheses(String value)
            throws IncorrectExpressionException {
        //Некорректные скобки (25,5)3, 3(25,5), (25)3, 3(25), (), )(, )5(, )5.5(

        Pattern pattern = Pattern.compile(INCORRECT_PARENTHESES_VERSION_ONE);
        Matcher matcher = pattern.matcher(value);

        if (matcher.find()) {
            throwIncorrectExpressionException(matcher, value, INCORRECT_PARENTHESES_STRING);
        }

        pattern = Pattern.compile(INCORRECT_PARENTHESES_VERSION_TWO);
        matcher = pattern.matcher(value);

        if (matcher.find()) {
            throwIncorrectExpressionException(matcher, value, INCORRECT_PARENTHESES_STRING);
        }

        pattern = Pattern.compile(INCORRECT_PARENTHESES_VERSION_THREE);
        matcher = pattern.matcher(value);

        if (matcher.find()) {
            throwIncorrectExpressionException(matcher, value, INCORRECT_PARENTHESES_STRING);
        }

        pattern = Pattern.compile(INCORRECT_PARENTHESES_VERSION_FOUR);
        matcher = pattern.matcher(value);

        if (matcher.find()) {
            throwIncorrectExpressionException(matcher, value, INCORRECT_PARENTHESES_STRING);
        }

        pattern = Pattern.compile(INCORRECT_PARENTHESES_VERSION_FIVE);
        matcher = pattern.matcher(value);

        if (matcher.find()) {
            throwIncorrectExpressionException(matcher, value, INCORRECT_PARENTHESES_STRING);
        }

        pattern = Pattern.compile(INCORRECT_PARENTHESES_VERSION_SIX);
        matcher = pattern.matcher(value);

        if (matcher.find()) {
            throwIncorrectExpressionException(matcher, value, INCORRECT_PARENTHESES_STRING);
        }

        pattern = Pattern.compile(INCORRECT_PARENTHESES_VERSION_SEVEN);
        matcher = pattern.matcher(value);

        if (matcher.find()) {
            throwIncorrectExpressionException(matcher, value, INCORRECT_PARENTHESES_STRING);
        }

        pattern = Pattern.compile(INCORRECT_PARENTHESES_VERSION_EIGHT);
        matcher = pattern.matcher(value);

        if (matcher.find()) {
            throwIncorrectExpressionException(matcher, value, INCORRECT_PARENTHESES_STRING);
        }
    }

    private void throwIncorrectExpressionException(Matcher matcher, String value, String exceptionString) throws IncorrectExpressionException {
        int start = matcher.start();
        int end = matcher.end();
        throw new IncorrectExpressionException(
                exceptionString + " " + value.substring(start, end));
    }

    public void stringHasTwoDotInOneCode(String value)
            throws IncorrectExpressionException {
        //А вдруг две точки типов 589.665656. или .589.665656 ?!

        Pattern pattern = Pattern.compile(TWO_DOT_IN_ONE_CODE_VERSION_ONE);
        Matcher matcher = pattern.matcher(value);

        if (matcher.find()) {
            throwIncorrectExpressionException(matcher, value, INCORRECT_DOT);
        }

        pattern = Pattern.compile(TWO_DOT_IN_ONE_CODE_VERSION_TWO);
        matcher = pattern.matcher(value);

        if (matcher.find()) {
            throwIncorrectExpressionException(matcher, value, INCORRECT_DOT);
        }
    }

    private void stringHasTwoDot(String value)
            throws IncorrectExpressionException {
        //А вдруг две точки 589..665656 ?!

        Pattern pattern = Pattern.compile(TWO_DOT);
        Matcher matcher = pattern.matcher(value);

        if (matcher.find()) {
            throwIncorrectExpressionException(matcher, value, "Выявлено две или более точки подряд");
        }
    }

    private void stringHasBadCharacters(String value)
            throws IncorrectExpressionException {
        //Проверяем нет ли букв и т.д.

        Pattern pattern = Pattern.compile(notValidRegExpString);
        Matcher matcher = pattern.matcher(value);

        if (matcher.find()) {
            throwIncorrectExpressionException(matcher, value, "Выявлены некорректные символы, например буквы.");
        }
    }

    private void parenthesisIncorrect(String value)
            throws IncorrectParenthesisException {
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
            throw new IncorrectParenthesisException(
                    "Количество открывающих скобок, не равно количеству закрывающих.");
        }
    }
}
