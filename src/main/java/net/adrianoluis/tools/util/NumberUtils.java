package net.adrianoluis.tools.util;

public final class NumberUtils {

    private NumberUtils() {}

    public static String toDigits(final String value) {
        return value.replaceAll("\\D+", "");
    }

    public static int digitsLength(final String value) {
        return toDigits(value).length();
    }
}
