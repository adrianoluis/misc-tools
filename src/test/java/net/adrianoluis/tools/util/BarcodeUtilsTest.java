package net.adrianoluis.tools.util;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.junit.Test;

import static junit.framework.TestCase.*;

public class BarcodeUtilsTest {

    @Test
    public void testToTypedLineWithInvalidInput() throws Throwable {
        final String barcode = "123456789012345678901234567890123456789012345";

        final String typedLine = BarcodeUtils.toTypedLine(barcode);
        assertEquals(barcode, typedLine);
    }

    @Test
    public void testToTypedLine47() throws Throwable {
        final String barcode = "23791620800000586754150094900407757300003200";
        final String expected = "23794.15009 94900.407753 73000.032000 1 62080000058675";

        final String typedLine = BarcodeUtils.toTypedLine(barcode);
        assertEquals(expected, typedLine);
    }

    @Test
    public void testToTypedLine48() throws Throwable {
        final String barcode = "81770000000010936599704113107970300143370831";
        final String expected = "81770000000-0 01093659970-2 41131079703-9 00143370831-8";

        final String typedLine = BarcodeUtils.toTypedLine(barcode);
        assertEquals(expected, typedLine);
    }

    @Test
    public void testToBarcodeWithInvalidInput() throws Throwable {
        final String typedLine = "12345678901234567890123456789012345678901234567";

        final String barcode = BarcodeUtils.toTypedLine(typedLine);
        assertEquals(typedLine, barcode);
    }

    @Test
    public void testToBarcode47() throws Throwable {
        final String typedLine = "23794.15009 94900.407753 73000.032000 1 62080000058675";
        final String expected = "23791620800000586754150094900407757300003200";

        final String barcode = BarcodeUtils.toBarcode(typedLine);
        assertEquals(expected, barcode);
    }

    @Test
    public void testToBarcode48() throws Throwable {
        final String typedLine = "81770000000-0 01093659970-2 41131079703-9 00143370831-8";
        final String expected = "81770000000010936599704113107970300143370831";

        final String barcode = BarcodeUtils.toBarcode(typedLine);
        assertEquals(expected, barcode);
    }

    @Test
    public void testExpiresAtTypedLine() throws Throwable {
        final String typedLine = "23794.15009 94900.407753 73000.032000 1 62080000058675";
        final LocalDate expected = new LocalDate(2014, DateTimeConstants.OCTOBER, 6);

        final LocalDate expiresAt = BarcodeUtils.expiresAt(typedLine);
        assertEquals(expected, expiresAt);
    }

    @Test
    public void testExpiresAtBarcode() throws Throwable {
        final LocalDate expected = new LocalDate(2014, DateTimeConstants.OCTOBER, 6);

        final String barcode = "23791620800000586754150094900407757300003200";

        final LocalDate expiresAt = BarcodeUtils.expiresAt(barcode);
        assertEquals(expected, expiresAt);
    }

    @Test
    public void testDocumentValueTypedLine47() throws Throwable {
        final double expected = 586.75;

        final String typedLine = "23794.15009 94900.407753 73000.032000 1 62080000058675";

        final double documentValue = BarcodeUtils.documentValue(typedLine);
        assertEquals(expected, documentValue);
    }

    @Test
    public void testDocumentValueBarcode47() throws Throwable {
        final double expected = 586.75;

        final String barcode = "23791620800000586754150094900407757300003200";

        final double documentValue = BarcodeUtils.documentValue(barcode);
        assertEquals(expected, documentValue);
    }

    @Test
    public void testDocumentValueTypedLine48() throws Throwable {
        final double expected = 1.09;

        final String typedLine = "81770000000-0 01093659970-2 41131079703-9 00143370831-8";

        final double documentValue = BarcodeUtils.documentValue(typedLine);
        assertEquals(expected, documentValue);
    }

    @Test
    public void testDocumentValueBarcode48() throws Throwable {
        final double expected = 0.0;

        final String barcode = "81770000000010936599704113107970300143370831";

        final double documentValue = BarcodeUtils.documentValue(barcode);
        assertEquals(expected, documentValue);
    }

    @Test
    public void testIsValidDocketDocumentWithInvalidInput() throws Throwable {
        final String typedLine = "123456789012345678901234567890123456789012345678";

        assertFalse(BarcodeUtils.isValidDocketDocument(typedLine));
    }

    @Test
    public void testIsValidDocketDocumentTrue() throws Throwable {
        final String typedLine = "23794.15009 94900.407753 73000.032000 1 62080000058675";

        final boolean isValidDocketDocument = BarcodeUtils.isValidDocketDocument(typedLine);
        assertTrue(isValidDocketDocument);
    }

    @Test
    public void testIsValidDocketDocumentFalse() throws Throwable {
        final String typedLine = "23791.15009 94900.407753 73000.032000 1 62080000058675";

        final boolean isValidDocketDocument = BarcodeUtils.isValidDocketDocument(typedLine);
        assertFalse(isValidDocketDocument);
    }

    @Test
    public void testIsValidContractDocumentWithInvalidInput() throws Throwable {
        final String typedLine = "1234567890123456789012345678901234567890123456789";

        final boolean isValidContractDocument = BarcodeUtils.isValidContractDocument(typedLine);
        assertFalse(isValidContractDocument);
    }

    @Test
    public void testIsValidContractDocumentTrue() throws Throwable {
        final String typedLine = "81770000000-0 01093659970-2 41131079703-9 00143370831-8";

        final boolean isValidContractDocument = BarcodeUtils.isValidContractDocument(typedLine);
        assertTrue(isValidContractDocument);
    }

    @Test
    public void testIsValidContractDocumentFalse() throws Throwable {
        final String typedLine = "81770000000-0 01093659970-2 41131079703-9 00143370831-1";

        final boolean isValidContractDocument = BarcodeUtils.isValidContractDocument(typedLine);
        assertFalse(isValidContractDocument);
    }

    @Test
    public void testIsValidContractDocumentWithNull() throws Throwable {
        assertFalse(BarcodeUtils.isValidContractDocument(null));
    }

    @Test
    public void testIsValidDocketDocumentWithNull() throws Throwable {
        assertFalse(BarcodeUtils.isValidDocketDocument(null));
    }

    @Test
    public void testIsValidBarcodeWithNull() throws Throwable {
        assertFalse(BarcodeUtils.isValidBarcode(null));
    }

    @Test
    public void testIsValidTypedLineWithNull() throws Throwable {
        assertFalse(BarcodeUtils.isValidTypedLine(null));
    }

}
