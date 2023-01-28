package dk.shadow.utils;

public class Format {

    public static String formatNum(double number) {
        String minus = "";
        if (number < 0) {
            minus = "-";
            number = Math.abs(number);
        }
        int zeros = (int) (Math.log10(number));
        int n = zeros / 3;
        if (n < 1) {
            return String.format("%s%.0f", minus, number);
        } else {
            String[] abbreviations = new String[]{"", "K", "M", "B", "T"};
            return String.format("%s%.2f%s", minus, number / Math.pow(1000, n), abbreviations[n]);
        }
    }

}