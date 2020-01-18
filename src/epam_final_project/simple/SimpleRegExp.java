package epam_final_project.simple;

import java.util.ArrayList;
import java.util.List;

public class SimpleRegExp {
    private String notValidRegExpString = "[^\\d ()+\\-.,*/^]+";
    //По моему мнению, это выражение - Все кроме Цифр, пробела, скобок,
    // плюс, минус, точка, запятая, звездочка, slash, circumflexus (^)
    //Так и не понял, как написать backslash (включить в RegExp символ backslash),
    // чтобы потом заменить на slash, подскажите пожалуйста :-)
    private final String SPACES = "\\s+";
    private final String NO_SPACE = "";
    private final String COMMA = ",";
    private final String DOT = ".";
    //Хоть IDEA и предлагает сделать их все локальными, оставлю,
    // мне так больше нравиться, когда все в одном месте
    //А как все таки правильнее? локальные переменные или все в одном месте?

    private List<String> notValidDots = new ArrayList<String>() {
        {
            this.add("[\\d]+[.][\\d]+[.]");
            //Для 589.665656.
            this.add("[.][\\d]+[.][\\d]+");
            //Для .589.665656
            this.add("[.]{2}");
            //Для 589..665656
        }
    };

    private List<String> notValidOperators = new ArrayList<String>() {
        {
            //Сколько не пытался собрать циклом в цикле / и ^ для того, чтобы сделать RegExp,
            // ак и не смог поэтому хардкод!

            // *-+/^
            this.add("[*]{2}");
            this.add("[*][-]");
            this.add("[*][+]");
            this.add("[*][/]");
            this.add("[*][\\^]");

            // *-+/^
            this.add("[+][*]");
            this.add("[+][-]");
            this.add("[+]{2}");
            this.add("[+][/]");
            this.add("[+][\\^]");

            // *-+/^
            this.add("[-][*]");
            this.add("[-]{2}");
            this.add("[-][+]");
            this.add("[-][/]");
            this.add("[-][\\^]");

            // *-+/^
            this.add("[\\^][*]");
            this.add("[\\^][-]");
            this.add("[\\^][+]");
            this.add("[\\^][/]");
            this.add("[\\^]{2}");

            // *-+/^
            this.add("[/][*]");
            this.add("[/][-]");
            this.add("[/][+]");
            this.add("[/]{2}");
            this.add("[/][\\^]");
        }
    };

    private List<String> notValidParenthesis = new ArrayList<String>() {
        {
            this.add("[(][)]");
            //Для в строке только ()
            this.add("[)][(]");
            //Для в строке только )(
            this.add("^[)]");
            //Для ) в начале строки
            this.add("[(]$");
            //Для ( в конце строки
            this.add("[)][\\d]");
            //Для )Цифра
            this.add("[\\d][(]");
            //Для Цифра(

            this.add("[(][*]");
            this.add("[(][/]");
            this.add("[(][\\^]");
            this.add("[*][)]");
            this.add("[/][)]");
            this.add("[\\^][)]");
            this.add("[-][)]");
            this.add("[+][)]");
            //Некорректные скобки и операции, вида СкобкаОперация или ОперацияСкобка

        }
    };

    public List<String> getNotValidDots() {
        return notValidDots;
    }

    public List<String> getNotValidOperators() {
        return notValidOperators;
    }

    public List<String> getNotValidParenthesis() {
        return notValidParenthesis;
    }

    public String getNotValidRegExpString() {
        return notValidRegExpString;
    }

    public String getSPACES() {
        return SPACES;
    }

    public String getNO_SPACE() {
        return NO_SPACE;
    }

    public String getCOMMA() {
        return COMMA;
    }

    public String getDOT() {
        return DOT;
    }
}
