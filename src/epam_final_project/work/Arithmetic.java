package epam_final_project.work;

import java.util.HashSet;
import java.util.Set;

public class Arithmetic {

    private Set<String> arithmeticOperations = new HashSet<String>() {
        {
            this.add("+");
            this.add("-");
            this.add("*");
            this.add("/");
            this.add("^");
        }
    };

    public Set<String> getOperations() {
        return arithmeticOperations;
    }

    public double calculation(double operator1, double operator2, String Operation) {
        switch (Operation) {
            case "+":
                return operator1 + operator2;

            case "-":
                return operator1 - operator2;

            case "*":
                return operator1 * operator2;

            case "/":
                return operator1 / operator2;

            case "^":
                return Math.pow(operator1, operator2);
        }
        return 0.0;
    }
}
