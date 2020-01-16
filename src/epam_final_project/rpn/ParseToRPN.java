package epam_final_project.rpn;

import epam_final_project.exception.IncorrectExpressionException;
import epam_final_project.exception.IncorrectParenthesisException;

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
        if (token.equals("*") || token.equals("/")) return 3;
        if (token.equals("^")) return 4;
        return 5;
    }

    public List<String> parseToRPN(String infix)
            throws IncorrectParenthesisException, IncorrectExpressionException {
        List<String> rnp_values = new ArrayList<>();
        Deque<String> stackOperators = new ArrayDeque<>();
        StringTokenizer tokenizer = new StringTokenizer(infix, delimiters, true);
        String prev = "";
        String curr;

        while (tokenizer.hasMoreTokens()) {
            curr = tokenizer.nextToken();
            if (!tokenizer.hasMoreTokens() && isOperator(curr)) {
                incorrectExpression();
            }

            if (isDelimiter(curr)) {
                if (curr.equals("(")) stackOperators.addFirst(curr);
                else if (curr.equals(")")) {

                    while (!stackOperators.peekFirst().equals("(")) {
                        rnp_values.add(stackOperators.removeFirst());
                        if (stackOperators.isEmpty()) {
                            incorrectParenthesis();
                        }
                    }
                    stackOperators.removeFirst();
                } else {
                    if (curr.equals("-") && (prev.equals("")
                            || (isDelimiter(prev) && !prev.equals(")")))) {
                        curr = "u-";
                    } else {

                        while (!stackOperators.isEmpty()
                                && (priority(curr) <= priority(stackOperators.peekFirst()))) {
                            rnp_values.add(stackOperators.removeFirst());
                        }
                    }
                    stackOperators.addFirst(curr);
                }

            } else {
                rnp_values.add(curr);
            }

            prev = curr;

        }

        while (!stackOperators.isEmpty()) {
            if (isOperator(stackOperators.peekFirst())) {
                rnp_values.add(stackOperators.removeFirst());
            } else {
                incorrectParenthesis();
            }
        }

        //Добавляю секретный ингридиент
        rnp_values.add(RPN_ARRAY);
        return rnp_values;
    }

    private void incorrectExpression() throws IncorrectExpressionException {
        throw new IncorrectExpressionException("Некорректное выражение");
    }

    private void incorrectParenthesis() throws IncorrectParenthesisException {
        throw new IncorrectParenthesisException("Скобки не согласованы");
    }

}
