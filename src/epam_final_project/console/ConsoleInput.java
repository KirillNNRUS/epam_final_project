package epam_final_project.console;

import java.util.Scanner;

public class ConsoleInput {
    private ConsoleOutput consoleOutput = new ConsoleOutput();
    Scanner in = new Scanner(System.in);

    public String scannerInputString() {
        return in.nextLine();
    }

    public int scannerInputToInt() {
        String temp = scannerInputString();

        try {
            return Integer.parseInt(temp);
        } catch (Exception e) {
            return -1;
        }
    }

    public void scannerClose() {
        in.close();
    }
}
