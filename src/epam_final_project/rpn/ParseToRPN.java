package epam_final_project.rpn;

import epam_final_project.exception.DivisionByZero;
import epam_final_project.exception.ParenthesisException;

import java.util.*;

public class ParseToRPN {
    private String operators = "+-*/^";
    private String delimiters = "() " + operators;
    private boolean isCorrectExpression = false;


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

    public List<String> parseToRPN(String infix) throws ParenthesisException {
        isCorrectExpression = false;
        List<String> rnp_values = new ArrayList<>();
        Deque<String> stackOperators = new ArrayDeque<>();
        StringTokenizer tokenizer = new StringTokenizer(infix, delimiters, true);
        String prev = "";
        String curr;
        while (tokenizer.hasMoreTokens()) {
            curr = tokenizer.nextToken();
            if (!tokenizer.hasMoreTokens() && isOperator(curr)) {
                System.out.println("Некорректное выражение.");

                return rnp_values;
            }
            if (curr.equals(" ")) continue;
            else if (isDelimiter(curr)) {
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
            if (isOperator(stackOperators.peek())) rnp_values.add(stackOperators.pop());
            else {
                System.out.println("Скобки не согласованы.");
                return rnp_values;
            }
        }
        isCorrectExpression = true;
        return rnp_values;
    }

    public Double calc(List<String> value) throws DivisionByZero {
        Deque<Double> stackOperators = new ArrayDeque<>();
        for (String x : value) {
            if (x.equals("+")) {
                stackOperators.push(stackOperators.pop() + stackOperators.pop());
            } else if (x.equals("^")) {
                Double b = stackOperators.pop(), a = stackOperators.pop();
                stackOperators.push(Math.pow(a, b));
            } else if (x.equals("-")) {
                Double b = stackOperators.pop(), a = stackOperators.pop();
                stackOperators.push(a - b);
            } else if (x.equals("*")) {
                stackOperators.push(stackOperators.pop() * stackOperators.pop());
            } else if (x.equals("/")) {
                Double b = stackOperators.pop(), a = stackOperators.pop();
                if (b == 0) {
                    throw new DivisionByZero("Нельзя делать на \"0\"");
                }
                stackOperators.push(a / b);
            } else if (x.equals("u-")) {
                stackOperators.push(-stackOperators.pop());
            } else {
                stackOperators.push(Double.valueOf(x));
            }
        }
        return stackOperators.pop();
    }

//    class Ideone {
//
//
//        public void main(String[] args) {
//            Scanner in = new Scanner(System.in);
////    String s = "( -7 ) ^ - 3 + ( ( 5 + 0.3 ) - ( (-7) + (-3) ) )";
//            String s = "78.1 / 3";
//            ExpressionParser n = new ExpressionParser();
//            List<String> expression = n.parse(s);
//            boolean flag = n.flag;
//            if (flag) {
//                for (String x : expression) System.out.print(x + " ");
//                System.out.println();
//                System.out.println(calc(expression));
//            }
//        }
//    }
}
