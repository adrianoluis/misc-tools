package net.adrianoluis.tools.util;

public class ModuloUtils {

    private ModuloUtils() {
    }

    public static String mod10(String number) {
        // XXX important to reverse the string
        number = new StringBuffer(number).reverse().toString();

        int sum = 0;
        int weight = 2;
        int plus;

        for (char c : number.toCharArray()) {
            plus = Character.getNumericValue(c) * weight;

            if (plus >= 10) {
                plus = (plus - 10) + 1;
            }

            sum += plus;

            if (weight == 2) {
                weight = 1;
            } else {
                weight = 2;
            }

        }

        int digit = 10 - (sum % 10);

        if (digit == 10) {
            digit = 0;
        }

        return String.valueOf(digit);
    }

    public static String mod11(String number) {
        // XXX important to reverse the string
        number = new StringBuffer(number).reverse().toString();

        int sum = 0;
        int weight = 2;
        int base = 9;

        for (char c : number.toCharArray()) {
            sum += Character.getNumericValue(c) * weight;

            if (weight < base) {
                weight++;
            } else {
                weight = 2;
            }

        }

        int digit = 11 - (sum % 11);

        if (digit > base) {
            digit = 1;
        }

        return String.valueOf(digit);
    }
}
