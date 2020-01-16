package epam_final_project.console;

public class ConsoleOutput {
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
}
