package epam_final_project.rpn;

import epam_final_project.exception.IncorrectExpressionException;
import epam_final_project.exception.ParenthesisException;

import java.util.*;

public class ParseToRPN {
    private String operators = "+-*/^";
    private String delimiters = "()" + operators;
    private final String RPN_ARRAY = "RPNArray";

    private boolean isDelimiter(String token) {
        if (token.length() != 1) return false;
        for (int i = 0; i < delimiters.length(); i++) {
            if (token.charAt(0) == delimiters.charAt(i)) return true;
        }
        return false;
    }

    private boolean isOperator(String token) {
        if (token.equals("u-")) return true;
        for (int i = 0; i < operators.length(); i++) {
            if (token.charAt(0) == operators.charAt(i)) return true;
        }
        return false;
    }

    private int priority(String token) {
        if (token.equals("(")) return 1;
        if (token.equals("+") || token.equals("-")) return 2;
        if (token.equals("*") || token.equals("/") || token.equals("^")) return 3;
        return 4;
    }

    public List<String> parseToRPN(String infix)
            throws ParenthesisException, IncorrectExpressionException {
        List<String> rnp_values = new ArrayList<>();
        Deque<String> stackOperators = new ArrayDeque<>();
        StringTokenizer tokenizer = new StringTokenizer(infix, delimiters, true);
        String prev = "";
        String curr;

        while (tokenizer.hasMoreTokens()) {
            curr = tokenizer.nextToken();
            if (!tokenizer.hasMoreTokens() && isOperator(curr)) {
                throw new IncorrectExpressionException("Некорректное выражение");
            }

            if (isDelimiter(curr)) {
                if (curr.equals("(")) stackOperators.push(curr);
                else if (curr.equals(")")) {
                    while (!stackOperators.peek().equals("(")) {
                        rnp_values.add(stackOperators.pop());
                        if (stackOperators.isEmpty()) {
                            throw new ParenthesisException("Скобки не согласованы");
                        }
                    }
                    stackOperators.pop();
                } else {
                    if (curr.equals("-") && (prev.equals("")
                            || (isDelimiter(prev) && !prev.equals(")")))) {
                        curr = "u-";
                    } else {
                        while (!stackOperators.isEmpty()
                                && (priority(curr) <= priority(stackOperators.peek()))) {
                            rnp_values.add(stackOperators.pop());
                        }
                    }
                    stackOperators.push(curr);
                }

            } else {
                rnp_values.add(curr);
            }

            prev = curr;

        }

        while (!stackOperators.isEmpty()) {
            if (isOperator(stackOperators.peek())) {
                rnp_values.add(stackOperators.pop());
            } else {
                throw new ParenthesisException("Скобки не согласованы");
            }
        }

        //Добавляю секретный ингридиент
        rnp_values.add(RPN_ARRAY);
        return rnp_values;
    }
}
