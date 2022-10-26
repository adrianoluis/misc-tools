package net.adrianoluis.tools.util;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;

import static net.adrianoluis.tools.util.ModuloUtils.mod10;
import static net.adrianoluis.tools.util.ModuloUtils.mod11;
import static net.adrianoluis.tools.util.NumberUtils.digitsLength;
import static net.adrianoluis.tools.util.NumberUtils.toDigits;

/**
 * This class validate a barcode and convert it to "Linha Digitável".
 *
 * @author adriano
 * @since Set 25, 2014
 */
public final class BarcodeUtils {

    private BarcodeUtils() {
    }

    /**
     * Check if a barcode string is of type ITF.
     *
     * @param code Barcode string
     * @return <code>true</code> if is a valid string, <code>false</code> otherwise
     */
    public static boolean isValidBarcode(final String code) {
        return null != code && digitsLength(code) == 44;
    }

    public static boolean isValidTypedLine(final String code) {
        return null != code && (
                code.matches("^([0-9]{5})(\\.)?([0-9]{5})(\\s)?([0-9]{5})(\\.)?([0-9]{6})(\\s)?([0-9]{5})(\\.)?([0-9]{6})(\\s)?([0-9])(\\s)?([0-9]{14})$") ||
                        code.matches("^([0-9]{11})(-)?([0-9])(\\s)?([0-9]{11})(-)?([0-9])(\\s)?([0-9]{11})(-)?([0-9])(\\s)?([0-9]{11})(-)?([0-9])$"));
    }

    /**
     * Check if the provided number is a valid "Boleto" number.
     *
     * @param code Typed Line or Barcode to extract
     * @return <code>true</code> if is a valid string, <code>false</code> otherwise
     */
    public static boolean isValidDocketDocument(String code) {
        if (!isValidTypedLine(code) && !isValidBarcode(code)) {
            return false;
        }

        code = toDigits(code);

        final String realCode = code.substring(0, 4) + code.substring(5);
        final String validationDigit = code.substring(4, 5);

        return mod11(realCode).equals(validationDigit);
    }

    /**
     * Check if the provided number is a valid "Convênio" number.
     *
     * @param typedLine Typed Line to extract
     * @return <code>true</code> if is a valid string, <code>false</code> otherwise
     */
    public static boolean isValidContractDocument(String typedLine) {
        if (!isValidTypedLine(typedLine) && (null == typedLine || typedLine.length() != 48)) {
            return false;
        }

        typedLine = toDigits(typedLine);

        final String codeBlock1 = typedLine.substring(0, 11);
        final String codeBlock2 = typedLine.substring(12, 23);
        final String codeBlock3 = typedLine.substring(24, 35);
        final String codeBlock4 = typedLine.substring(36, 47);

        final String validationDigit1 = typedLine.substring(11, 12);
        final String validationDigit2 = typedLine.substring(23, 24);
        final String validationDigit3 = typedLine.substring(35, 36);
        final String validationDigit4 = typedLine.substring(47);

        return mod10(codeBlock1).equals(validationDigit1) && mod10(codeBlock2).equals(validationDigit2) &&
                mod10(codeBlock3).equals(validationDigit3) && mod10(codeBlock4).equals(validationDigit4);
    }

    /**
     * Try to format the barcode string of type ITF into "Linha Digitável"
     *
     * @param barcode Barcode string
     * @return A well formatted version of the barcode given
     */
    public static String toTypedLine(String barcode) {
        if (null == barcode || barcode.trim().isEmpty()) {
            return barcode;
        }

        // extract only numbers
        barcode = toDigits(barcode);

        if (!isValidBarcode(barcode)) {
            return barcode;
        }

        if (isValidDocketDocument(barcode)) {
            final String codeBlock1 = barcode.substring(0, 4) + barcode.charAt(19);
            final String codeBlock2 = barcode.substring(20, 24);
            final String codeBlock3 = barcode.substring(24, 29);
            final String codeBlock4 = barcode.substring(29, 34);
            final String codeBlock5 = barcode.substring(34, 39);
            final String codeBlock6 = barcode.substring(39, 44);
            final String codeBlock7 = barcode.substring(4, 5);
            final String codeBlock8 = barcode.substring(5, 19);

            final String validationDigit1 = mod10(codeBlock1 + codeBlock2);
            final String validationDigit2 = mod10(codeBlock3 + codeBlock4);
            final String validationDigit3 = mod10(codeBlock5 + codeBlock6);

            return String.format("%1$s.%2$s%9$s %3$s.%4$s%10$s %5$s.%6$s%11$s %7$s %8$s",
                    codeBlock1, codeBlock2, codeBlock3, codeBlock4, codeBlock5, codeBlock6, codeBlock7, codeBlock8,
                    validationDigit1, validationDigit2, validationDigit3);
        } else {
            final String codeBlock1 = barcode.substring(0, 11);
            final String codeBlock2 = barcode.substring(11, 22);
            final String codeBlock3 = barcode.substring(22, 33);
            final String codeBlock4 = barcode.substring(33, 44);

            final String validationDigit1 = mod10(codeBlock1);
            final String validationDigit2 = mod10(codeBlock2);
            final String validationDigit3 = mod10(codeBlock3);
            final String validationDigit4 = mod10(codeBlock4);

            return String.format("%1$s-%5$s %2$s-%6$s %3$s-%7$s %4$s-%8$s",
                    codeBlock1, codeBlock2, codeBlock3, codeBlock4,
                    validationDigit1, validationDigit2, validationDigit3, validationDigit4);
        }

    }

    /**
     * Try to parse the "Linha Digitável" to a barcode
     *
     * @param typedLine "Linha Digitável" string
     * @return Corresponding barcode
     */
    public static String toBarcode(String typedLine) {
        if (!isValidTypedLine(typedLine)) {
            return typedLine;
        }

        typedLine = toDigits(typedLine);

        if (isValidContractDocument(typedLine)) {
            return typedLine.substring(0, 11) +
                    typedLine.substring(12, 23) +
                    typedLine.substring(24, 35) +
                    typedLine.substring(36, 47);
        } else {
            String code = typedLine.substring(0, 4) +
                    typedLine.substring(32, 47) +
                    typedLine.substring(4, 9) +
                    typedLine.substring(10, 20) +
                    typedLine.substring(21, 31);

            final String realCode = code.substring(0, 4) + code.substring(5);
            final String validationDigit = code.substring(4, 5);

            if (!mod11(realCode).equals(validationDigit)) {
                code = typedLine;
            }

            return code;
        }

    }

    /**
     * Returns the expiration date of a "Boleto" document.
     *
     * @param code Typed Line or Barcode to extract
     * @return a {@link java.util.Date} representing the expiration date
     */
    public static LocalDate expiresAt(String code) {
        LocalDate expiresAt = null;

        if (isValidDocketDocument(code)) {
            final int daysToExpire = Integer.parseInt(toBarcode(code).substring(5, 9));
            final LocalDate calendar = new LocalDate(1997, DateTimeConstants.OCTOBER, 7);
            expiresAt = calendar.plusDays(daysToExpire);
        }

        return expiresAt;
    }

    /**
     * Returns the document value to be paid
     *
     * @param code Typed Line or Barcode to extract
     * @return the document value as double
     */
    public static double documentValue(String code) {
        double value = 0.0;

        String barcode = code;

        if (!isValidBarcode(code)) {
            barcode = toBarcode(code);
        }

        if (isValidDocketDocument(barcode)) {
            value = Double.parseDouble(barcode.substring(9, 19)) / 100;
        } else if (isValidContractDocument(code)) { // so valida se for linha digitavel
            value = Double.parseDouble(barcode.substring(5, 15)) / 100;
        }

        return value;
    }
}
