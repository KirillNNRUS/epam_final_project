package epam_final_project.work;

public class ParseString {
    private double firstDouble = 0;
    private double secondDouble = 0;
    private String firstString = "";
    private String secondString = "";
    private String miniString = "";
    private int begin = 0;
    private int end = 0;


    public String result(String value) {
        String temp = "";
        if (value == null || value.equals("")) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder(value);
        begin = stringBuilder.lastIndexOf("(");
        if (begin >= 0 && begin < stringBuilder.length()) {
            end = stringBuilder.indexOf(")", begin);
        }

       temp = stringBuilder.substring(begin, end + 1);

        return stringBuilder.substring(begin, end + 1);
    }

    private void findMiniString(String value) {
        miniString = "";
    }

    private double miniResult() {
        return 0;
    }
}
