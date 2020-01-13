package epam_final_project;

import epam_final_project.work.ParseString;

public class SecMain {
    public static void main(String[] args) {
        ParseString parseString = new ParseString();
        String value = "(-3 + 9.0)";
        value = value.replaceAll(" ", "");
        System.out.println(parseString.result(value));
        value = value.substring(1, value.length() -1);
        System.out.println(value);
        
    }
}
