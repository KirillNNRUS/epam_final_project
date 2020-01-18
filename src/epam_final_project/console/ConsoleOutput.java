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
        System.out.println(simpleStrings.getATTENTION_01());
        System.out.println(simpleStrings.getATTENTION_02());
        System.out.println(simpleStrings.getATTENTION_03());
        System.out.println(simpleStrings.getATTENTION_04());
    }

    public void printYesOrNo() {
        System.out.println(simpleStrings.getYES_OR_NO());
    }
}
