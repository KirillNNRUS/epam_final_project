package epam_final_project.rpn;

import epam_final_project.exception.DivisionByZeroException;
import epam_final_project.exception.IncorrectRPNArrayException;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class CalculateRPN {
    private final String RPN_ARRAY = "RPNArray";

    public Double calculate(List<String> value) throws DivisionByZeroException, IncorrectRPNArrayException {
        if (value == null || value.size() == 0) {
            throw new IncorrectRPNArrayException("Получена некорректная Обратная польская запись");
        }

        if (!value.get(value.size() - 1).equals(RPN_ARRAY)) {
            throw new IncorrectRPNArrayException("Получена некорректная Обратная польская запись");
        }

        List<String> correctValue = value;
        //Удаляем секретный ингридиент
        correctValue.remove(correctValue.size() - 1);

        Deque<Double> stackOperators = new ArrayDeque<>();
        for (String x : correctValue) {
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
                    throw new DivisionByZeroException("Нельзя делить на \"0\"");
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
}
