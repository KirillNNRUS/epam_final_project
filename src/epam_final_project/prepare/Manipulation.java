package epam_final_project.prepare;

import epam_final_project.simple.SimpleRegExp;
import epam_final_project.simple.SimpleStrings;

public class Manipulation {
    SimpleStrings simpleStrings = new SimpleStrings();
    SimpleRegExp simpleRegExp = new SimpleRegExp();


    public String allStringManipulation(String value) {
        value = removeSpaces(value);
        value = replaceCommaDot(value);
        return value;
    }

    private String removeSpaces(String value) {
        //Удаляю пробелы
        return value.replaceAll(simpleRegExp.getSPACES(), simpleRegExp.getNO_SPACE());
    }

    private String replaceCommaDot(String value) {
        //Делаю из запятой точку
        return value.replaceAll(simpleRegExp.getCOMMA(), simpleRegExp.getDOT());
    }
}