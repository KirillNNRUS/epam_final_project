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

    private List<String> notValidOperations = new ArrayList<String>() {
        {
            //Операции в начале или к в конце выражения
            this.add("[*]$");
            this.add("^[*]");

            this.add("[/]$");
            this.add("^[/]");

            this.add("[-]$");
            this.add("^[-]");
            //Ставлю ошибку - в начала т.к. по ТЗ нужно писать (-4) и значит это ошибка


            this.add("[+]$");
            this.add("^[+]");
            //Может зря, но + в начале тоже как ошибка, возможно пользователь  что-то забыл

            this.add("[\\^]$");
            this.add("^[\\^]");
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

    private List<String> notValidParenthesisWithoutOperations = new ArrayList<String>() {
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
        }
    };
    private List<String> notValidParenthesisWithOperations = new ArrayList<String>() {
        {
            this.add("[(][*]");
            this.add("[(][/]");
            this.add("[(][\\^]");
            this.add("[(][+]");
            // (+ тоже решил расценивать как ошибку, что пользователь что-то забыл
            // (-ЗНАЧЕНИЕ это по ТЗ -ЗНАЧЕНИЕ, поэтому не включаю
            this.add("[*][)]");
            this.add("[/][)]");
            this.add("[\\^][)]");
            this.add("[-][)]");
            this.add("[+][)]");
            //Некорректные скобки и операции, вида СкобкаОперация или ОперацияСкобка
            this.add("[(][\\d]{1,}[.]{0,}[\\d]{0,}[)]");
            //Так же добавил выражение вида (ЧИСЛО), т.к. по ТЗ (-ЧИСЛО) есть,
            //а тут возможно пользователь, что-то забыл
        }
    };

    public List<String> getNotValidParenthesisWithOperations() {
        return notValidParenthesisWithOperations;
    }

    public List<String> getNotValidDots() {
        return notValidDots;
    }

    public List<String> getNotValidOperators() {
        return notValidOperators;
    }

    public List<String> getNotValidParenthesisWithoutOperations() {
        return notValidParenthesisWithoutOperations;
    }

    public List<String> getNotValidOperations() {
        return notValidOperations;
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
