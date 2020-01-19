package epam_final_project.simple;

public class SimpleStrings {
    private final String ATTENTION_01 = "Обратите внимание! Запятые буду заменены на точки!";
    private final String ATTENTION_02 = "Обратите внимание! Знак делить это \"/\"!";
    private final String ATTENTION_03 = "Обратите внимание! Минусовые значения, необходимо писать в скобках, например 5 * (-4)";
    private final String ATTENTION_04 = "Обратите внимание! Если Вы забудете знак минус 5 * (4). Программа воспримет это как 5 * 4";
    private final String DIVISION_BY_ZERO = "Нельзя делить на \"0\"";
    private final String ENTER_EXPRESSION = "Введите математическое выражение";
    private final String INCORRECT_DOT = "Некорректная расстановка точек";
    private final String INCORRECT_EXPRESSION = "Некорректное выражение";
    private final String INCORRECT_EXPRESSION_EMPTY = "Некорректное выражение - пустая строка";
    private final String INCORRECT_OPERATION = "Некорректная операция";
    private final String INCORRECT_OPERATION_BEGIN_OR_END = "Знак операции в начале или конце выражения";
    private final String INCORRECT_PARENTHESES = "Скобки не согласованы";
    private final String INCORRECT_PARENTHESES_BEGIN_OR_END = "Некорректная скобка, в начале или в конце строки : ";
    private final String INCORRECT_PARENTHESES_END = "Найдена закрывающая скобка, ранее чем открывающая.";
    private final String INCORRECT_PARENTHESES_END_AND_BEGIN = "Количество открывающих скобок, не равно количеству закрывающих.";
    private final String INCORRECT_PARENTHESES_STRING = "Некорректная расстановка скобок";
    private final String INCORRECT_RPN = "Получена некорректная Обратная польская запись";
    private final String INCORRECT_SYMBOLS = "Выявлены некорректные символы.";
    private final String PROGRAM = "Данная программа может сосчитать введенное Вами математическое выражение";
    private final String RESULT_YOUR_EXPRESSION = "Результат Вашего выражения : ";
    private final String RPN_ARRAY = "RPNArray";
    private final String SECOND_TRY = "Хотите попробовать еще раз?";
    private final String TRY = "Хотите попробовать?";
    private final String YES_OR_NO = "Хотите попробовать еще раз?";
    private final String YOUR_EXPRESSION = "Ваше выражение : ";

    public String getINCORRECT_EXPRESSION() {
        return INCORRECT_EXPRESSION;
    }

    public String getINCORRECT_SYMBOLS() {
        return INCORRECT_SYMBOLS;
    }

    public String getATTENTION_04() {
        return ATTENTION_04;
    }

    public String getINCORRECT_PARENTHESES_END() {
        return INCORRECT_PARENTHESES_END;
    }

    public String getINCORRECT_PARENTHESES_END_AND_BEGIN() {
        return INCORRECT_PARENTHESES_END_AND_BEGIN;
    }

    public String getDIVISION_BY_ZERO() {
        return DIVISION_BY_ZERO;
    }

    public String getINCORRECT_EXPRESSION_EMPTY() {
        return INCORRECT_EXPRESSION_EMPTY;
    }

    public String getINCORRECT_RPN() {
        return INCORRECT_RPN;
    }

    public String getATTENTION_01() {
        return ATTENTION_01;
    }

    public String getATTENTION_02() {
        return ATTENTION_02;
    }

    public String getATTENTION_03() {
        return ATTENTION_03;
    }

    public String getPROGRAM() {
        return PROGRAM;
    }

    public String getRESULT_YOUR_EXPRESSION() {
        return RESULT_YOUR_EXPRESSION;
    }

    public String getYOUR_EXPRESSION() {
        return YOUR_EXPRESSION;
    }

    public String getYES_OR_NO() {
        return YES_OR_NO;
    }

    public String getTRY() {
        return TRY;
    }

    public String getSECOND_TRY() {
        return SECOND_TRY;
    }

    public String getRPN_ARRAY() {
        return RPN_ARRAY;
    }

    public String getENTER_EXPRESSION() {
        return ENTER_EXPRESSION;
    }

    public String getINCORRECT_PARENTHESES() {
        return INCORRECT_PARENTHESES;
    }

    public String getINCORRECT_PARENTHESES_STRING() {
        return INCORRECT_PARENTHESES_STRING;
    }

    public String getINCORRECT_PARENTHESES_BEGIN_OR_END() {
        return INCORRECT_PARENTHESES_BEGIN_OR_END;
    }

    public String getINCORRECT_DOT() {
        return INCORRECT_DOT;
    }

    public String getINCORRECT_OPERATION() {
        return INCORRECT_OPERATION;
    }

    public String getINCORRECT_OPERATION_BEGIN_OR_END() {
        return INCORRECT_OPERATION_BEGIN_OR_END;
    }
}
