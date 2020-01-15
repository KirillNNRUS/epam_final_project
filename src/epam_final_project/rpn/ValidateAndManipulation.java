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
    private final String spaces = "\\s+";
    private final String NoSpace = "";
    private final String comma = ",";
    private final String dot = ".";
    private final String twoDot = "[.]{2}";
    //Для 589..665656
    private final String twoDotInOneCodeVersion1 = "[\\d]+[.][\\d]+[.]";
    //Для 589.665656.
    private final String twoDotInOneCodeVersion2 = "[.][\\d]+[.][\\d]+";
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
        return value.replaceAll(spaces, NoSpace);
    }

    private String replaceCommaDot(String value) {
        //Делаю из запятой точку
        return value.replaceAll(comma, dot);
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

        Pattern pattern = Pattern.compile(twoDotInOneCodeVersion1);
        Matcher matcher = pattern.matcher(value);

        if (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            throw new InvalidCharactersEnteredException(
                    "Некорректная расстановка точек " + value.substring(start, end));
        }

        pattern = Pattern.compile(twoDotInOneCodeVersion2);
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

        Pattern pattern = Pattern.compile(twoDot);
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
