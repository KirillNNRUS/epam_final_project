package epam_final_project.rpn;

import epam_final_project.exception.DivisionByZeroException;
import epam_final_project.exception.IncorrectRPNArrayException;
import epam_final_project.simple.SimpleStrings;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class CalculateRPN {
    SimpleStrings simpleStrings = new SimpleStrings();

    public Double calculate(List<String> value) throws DivisionByZeroException, IncorrectRPNArrayException {
        if (value == null || value.size() == 0) {
            throw new IncorrectRPNArrayException("Получена некорректная Обратная польская запись");
        }

        if (!value.get(value.size() - 1).equals(simpleStrings.getRPN_ARRAY())) {
            throw new IncorrectRPNArrayException("Получена некорректная Обратная польская запись");
        }

        //Удаляем секретный ингридиент
        value.remove(value.size() - 1);

        Deque<Double> stackOperators = new ArrayDeque<>();
        for (String x : value) {
            switch (x) {
                case "+":
                    stackOperators.addFirst(stackOperators.removeFirst() + stackOperators.removeFirst());
                    break;
                case "^": {
                    Double b = stackOperators.removeFirst(), a = stackOperators.removeFirst();
                    stackOperators.addFirst(Math.pow(a, b));
                    break;
                }
                case "-": {
                    Double b = stackOperators.removeFirst(), a = stackOperators.removeFirst();
                    stackOperators.addFirst(a - b);
                    break;
                }
                case "*":
                    stackOperators.addFirst(stackOperators.removeFirst() * stackOperators.removeFirst());
                    break;
                case "/": {
                    Double b = stackOperators.removeFirst(), a = stackOperators.removeFirst();
                    if (b == 0) {
                        throw new DivisionByZeroException("Нельзя делить на \"0\"");
                    }
                    stackOperators.addFirst(a / b);
                    break;
                }
                case "u-":
                    stackOperators.addFirst(-stackOperators.removeFirst());
                    break;
                default:
                    stackOperators.addFirst(Double.valueOf(x));
                    break;
            }
        }
        return stackOperators.removeFirst();
    }
}
