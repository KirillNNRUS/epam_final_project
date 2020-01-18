package epam_final_project.work;

import epam_final_project.exception.IncorrectExpressionException;
import epam_final_project.exception.IncorrectParenthesisException;
import epam_final_project.simple.SimpleStrings;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateAndManipulation {
    SimpleStrings simpleStrings = new SimpleStrings();
    private String notValidRegExpString = "[^\\d ()+\\-.,*/^]+";
    //По моему мнению, это выражение - Все кроме Цифр, пробела, скобок,
    // плюс, минус, точка, запятая, звездочка, slash, circumflexus (^)
    //Так и не понял, как написать backslash (включить в RegExp символ backslash),
    // чтобы потом заменить на slash, подскажите пожалуйста :-)
    private final String SPACES = "\\s+";
    private final String NO_SPACE = "";
    private final String COMMA = ",";
    private final String DOT = ".";
    //Хоть IDEA и предлагает сделать их все локальными, оставлю,
    // мне так больше нравиться, когда все в одном месте
    //А как все таки правильнее? локальные переменные или все в одном месте?

    private List<String> notValidDots = new ArrayList<String>() {
        {
            this.add("[\\d]+[.][\\d]+[.]");
            //Для 589.665656.
            this.add("[.][\\d]+[.][\\d]+");
            //Для .589.665656
            this.add("[.]{2}");
            //Для 589..665656
        }
    };

    private List<String> notValidOperators = new ArrayList<String>() {
        {
            //Сколько не пытался собрать циклом в цикле / и ^ для того, чтобы сделать RegExp,
            // ак и не смог поэтому хардкод!

            // *-+/^
            this.add("[*]{2}");
            this.add("[*][-]");
            this.add("[*][+]");
            this.add("[*][/]");
            this.add("[*][\\^]");

            // *-+/^
            this.add("[+][*]");
            this.add("[+][-]");
            this.add("[+]{2}");
            this.add("[+][/]");
            this.add("[+][\\^]");

            // *-+/^
            this.add("[-][*]");
            this.add("[-]{2}");
            this.add("[-][+]");
            this.add("[-][/]");
            this.add("[-][\\^]");

            // *-+/^
            this.add("[\\^][*]");
            this.add("[\\^][-]");
            this.add("[\\^][+]");
            this.add("[\\^][/]");
            this.add("[\\^]{2}");

            // *-+/^
            this.add("[/][*]");
            this.add("[/][-]");
            this.add("[/][+]");
            this.add("[/]{2}");
            this.add("[/][\\^]");
        }
    };

    private List<String> notValidParenthesis = new ArrayList<String>() {
        {
            this.add("[(][)]");
            //Для в строке только ()
            this.add("[)][(]");
            //Для в строке только )(
            this.add("^[)]");
            //Для ) в начале строки
            this.add("[(]$");
            //Для ( в конце строки
            this.add("[)][\\d]");
            //Для )Цифра
            this.add("[\\d][(]");
            //Для Цифра(

            this.add("[(][*]");
            this.add("[(][/]");
            this.add("[(][\\^]");
            this.add("[*][)]");
            this.add("[/][)]");
            this.add("[\\^][)]");
            this.add("[-][)]");
            this.add("[+][)]");
            //Некорректные скобки и операции, вида СкобкаОперация или ОперацияСкобка

        }
    };

    public String allStringManipulation(String value) {
        value = removeSpaces(value);
        value = replaceCommaDot(value);
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
        doubleOperations(value);
        parenthesisIncorrect(value);
        incorrectParentheses(value);
    }

    private void doubleOperations(String value) throws IncorrectExpressionException {
        for (String operation : notValidOperators) {
            Pattern pattern = Pattern.compile(operation);
            Matcher matcher = pattern.matcher(value);

            if (matcher.find()) {
                throwIncorrectExpressionException(matcher, value,
                        simpleStrings.getINCORRECT_OPERATION());
            }
        }
    }

    private void incorrectParentheses(String value)
            throws IncorrectExpressionException {
        for (String parentheses : notValidParenthesis) {
            Pattern pattern = Pattern.compile(parentheses);
            Matcher matcher = pattern.matcher(value);

            if (matcher.find()) {
                if (parentheses.equals("^[)]") || parentheses.equals("[(]$")) {
                    throwIncorrectExpressionException(matcher, value,
                            simpleStrings.getINCORRECT_PARENTHESES_BEGIN_OR_END());
                }
                throwIncorrectExpressionException(matcher, value,
                        simpleStrings.getINCORRECT_PARENTHESES_STRING());
            }
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
        for (String dots : notValidDots) {
            Pattern pattern = Pattern.compile(dots);
            Matcher matcher = pattern.matcher(value);

            if (matcher.find()) {
                throwIncorrectExpressionException(matcher, value,
                        simpleStrings.getINCORRECT_DOT());
            }
        }
    }

    private void stringHasBadCharacters(String value)
            throws IncorrectExpressionException {
        //Проверяем нет ли букв и т.д.

        Pattern pattern = Pattern.compile(notValidRegExpString);
        Matcher matcher = pattern.matcher(value);

        if (matcher.find()) {
            throwIncorrectExpressionException(matcher, value, "Выявлены некорректные символы.");
        }
    }

    private void parenthesisIncorrect(String value)
            throws IncorrectParenthesisException {
        //Количество "(" должно быть равно количеству ")".

        int beginParenthesis = 0;
        int endParenthesis = 0;

        for (int i = 0; i < value.length(); i++) {
            if ((String.valueOf(value.charAt(i))).equals("(")) {
                beginParenthesis++;
            }
            if ((String.valueOf(value.charAt(i))).equals(")")) {
                endParenthesis++;
                //Доп проверка, если ) появилась раньше ( выброс исключения
                if (endParenthesis > beginParenthesis) {
                    throw new IncorrectParenthesisException(
                            "Найдена закрывающая скобка, ранее чем открывающая.");
                }
            }
        }

        if (beginParenthesis != endParenthesis) {
            throw new IncorrectParenthesisException(
                    "Количество открывающих скобок, не равно количеству закрывающих.");
        }
    }
}