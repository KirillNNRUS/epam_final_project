package epam_final_project.console;

import epam_final_project.simple.SimpleStrings;

public class ConsoleOutput {
    SimpleStrings simpleStrings = new SimpleStrings();

    public void consoleOutput(String... text) {
        StringBuilder stringBuilder = new StringBuilder();

        for (String value : text) {
            stringBuilder.append(" ").append(value);
        }

        System.out.println(stringBuilder);
    }

    public void printAttentions() {
        System.out.println("Обратите внимание! Запятые буду заменены на точки!");
        System.out.println("Обратите внимание! Знак делить это \"/\"!");
        System.out.println("Обратите внимание! Минусовые значения, необходимо писать в скобках, например 5 * (-4)");
    }

    public void printYesOrNo() {
        System.out.println(simpleStrings.getYES_OR_NO());
    }
}
