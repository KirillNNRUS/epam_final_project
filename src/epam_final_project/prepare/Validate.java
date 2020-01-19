package epam_final_project.prepare;

import epam_final_project.exception.IncorrectExpressionException;
import epam_final_project.exception.IncorrectParenthesisException;
import epam_final_project.simple.SimpleRegExp;
import epam_final_project.simple.SimpleStrings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {
    SimpleStrings simpleStrings = new SimpleStrings();
    SimpleRegExp simpleRegExp = new SimpleRegExp();

    public void allStringValidate(String value)
            throws IncorrectParenthesisException, IncorrectExpressionException {
        stringHasBadCharacters(value);
        doubleOperations(value);
        simpleParenthesisIncorrect(value);
        incorrectParenthesesWithoutOperations(value);
        incorrectParenthesesWithOperations(value);
        incorrectOperations(value);
        stringHasTwoDotInOneCode(value);
    }

    private void doubleOperations(String value) throws IncorrectExpressionException {
        for (String operation : simpleRegExp.getNotValidOperators()) {
            Pattern pattern = Pattern.compile(operation);
            Matcher matcher = pattern.matcher(value);

            if (matcher.find()) {
                throwIncorrectExpressionException(matcher, value,
                        simpleStrings.getINCORRECT_OPERATION());
            }
        }
    }

    private void incorrectParenthesesWithoutOperations(String value)
            throws IncorrectExpressionException {
        for (String parentheses : simpleRegExp.getNotValidParenthesisWithoutOperations()) {
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
    private void incorrectParenthesesWithOperations(String value)
            throws IncorrectExpressionException {
        for (String parentheses : simpleRegExp.getNotValidParenthesisWithoutOperations()) {
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

    private void incorrectOperations(String value)
            throws IncorrectExpressionException {
        for (String dots : simpleRegExp.getNotValidOperations()) {
            Pattern pattern = Pattern.compile(dots);
            Matcher matcher = pattern.matcher(value);

            if (matcher.find()) {
                throwIncorrectExpressionException(matcher, value,
                        simpleStrings.getINCORRECT_OPERATION_BEGIN_OR_END());
            }
        }
    }

    private void stringHasTwoDotInOneCode(String value)
            throws IncorrectExpressionException {
        for (String dots : simpleRegExp.getNotValidDots()) {
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

        Pattern pattern = Pattern.compile(simpleRegExp.getNotValidRegExpString());
        Matcher matcher = pattern.matcher(value);

        if (matcher.find()) {
            throwIncorrectExpressionException(matcher, value, simpleStrings.getINCORRECT_SYMBOLS());
        }
    }


    private void simpleParenthesisIncorrect(String value)
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
                    throw new IncorrectParenthesisException(simpleStrings.getINCORRECT_PARENTHESES_END());
                }
            }
        }

        if (beginParenthesis != endParenthesis) {
            throw new IncorrectParenthesisException(simpleStrings.getINCORRECT_PARENTHESES_END_AND_BEGIN());
        }
    }

    private void throwIncorrectExpressionException(Matcher matcher, String value, String exceptionString)
            throws IncorrectExpressionException {
        int start = matcher.start();
        int end = matcher.end();
        throw new IncorrectExpressionException(
                exceptionString + " " + value.substring(start, end));
    }
}