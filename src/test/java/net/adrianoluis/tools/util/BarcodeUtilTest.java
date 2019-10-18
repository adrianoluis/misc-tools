package net.adrianoluis.tools.util;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.junit.Test;

import static junit.framework.TestCase.*;

public class BarcodeUtilTest {

    @Test
    public void testToTypefulLine47() throws Throwable {
        final String barcode = "23791620800000586754150094900407757300003200";
        final String expected = "23794.15009 94900.407753 73000.032000 1 62080000058675";

        final String typefulLine = BarcodeUtil.toTypefulLine(barcode);
        assertEquals(expected, typefulLine);
    }

    @Test
    public void testToTypefulLine48() throws Throwable {
        final String barcode = "81770000000010936599704113107970300143370831";
        final String expected = "81770000000-0 01093659970-2 41131079703-9 00143370831-8";

        final String typefulLine = BarcodeUtil.toTypefulLine(barcode);
        assertEquals(expected, typefulLine);
    }

    @Test
    public void testToBarcode47() throws Throwable {
        final String typefulLine = "23794.15009 94900.407753 73000.032000 1 62080000058675";
        final String expected = "23791620800000586754150094900407757300003200";

        final String barcode = BarcodeUtil.toBarcode(typefulLine);
        assertEquals(expected, barcode);
    }

    @Test
    public void testToBarcode48() throws Throwable {
        final String typefulLine = "81770000000-0 01093659970-2 41131079703-9 00143370831-8";
        final String expected = "81770000000010936599704113107970300143370831";

        final String barcode = BarcodeUtil.toBarcode(typefulLine);
        assertEquals(expected, barcode);
    }

    @Test
    public void testExpiresAtTypefulLine() throws Throwable {
        final LocalDate expected = new LocalDate(2014, DateTimeConstants.OCTOBER, 6);

        final String typefulLine = "23794.15009 94900.407753 73000.032000 1 62080000058675";

        final LocalDate expiresAt = BarcodeUtil.expiresAt(typefulLine);
        assertEquals(expected, expiresAt);
    }

    @Test
    public void testExpiresAtBarcode() throws Throwable {
        final LocalDate expected = new LocalDate(2014, DateTimeConstants.OCTOBER, 6);

        final String barcode = "23791620800000586754150094900407757300003200";

        final LocalDate expiresAt = BarcodeUtil.expiresAt(barcode);
        assertEquals(expected, expiresAt);
    }

    @Test
    public void testDocumentValueTypefulLine47() throws Throwable {
        final double expected = 586.75;

        final String typefulLine = "23794.15009 94900.407753 73000.032000 1 62080000058675";

        final double documentValue = BarcodeUtil.documentValue(typefulLine);
        assertEquals(expected, documentValue);
    }

    @Test
    public void testDocumentValueBarcode47() throws Throwable {
        final double expected = 586.75;

        final String barcode = "23791620800000586754150094900407757300003200";

        final double documentValue = BarcodeUtil.documentValue(barcode);
        assertEquals(expected, documentValue);
    }

    @Test
    public void testDocumentValueTypefulLine48() throws Throwable {
        final double expected = 1.09;

        final String typefulLine = "81770000000-0 01093659970-2 41131079703-9 00143370831-8";

        final double documentValue = BarcodeUtil.documentValue(typefulLine);
        assertEquals(expected, documentValue);
    }

    @Test
    public void testDocumentValueBarcode48() throws Throwable {
        final double expected = 0.0;

        final String barcode = "81770000000010936599704113107970300143370831";

        final double documentValue = BarcodeUtil.documentValue(barcode);
        assertEquals(expected, documentValue);
    }

    @Test
    public void testIsValidDocketDocumentTrue() throws Throwable {
        final String typefulLine = "23794.15009 94900.407753 73000.032000 1 62080000058675";

        final boolean isValidContractDocument = BarcodeUtil.isValidDocketDocument(typefulLine);
        assertTrue(isValidContractDocument);
    }

    @Test
    public void testIsValidDocketDocumentFalse() throws Throwable {
        final String typefulLine = "23791.15009 94900.407753 73000.032000 1 62080000058675";

        final boolean isValidContractDocument = BarcodeUtil.isValidDocketDocument(typefulLine);
        assertFalse(isValidContractDocument);
    }

    @Test
    public void testIsValidContractDocumentTrue() throws Throwable {
        final String typefulLine = "81770000000-0 01093659970-2 41131079703-9 00143370831-8";

        final boolean isValidContractDocument = BarcodeUtil.isValidContractDocument(typefulLine);
        assertTrue(isValidContractDocument);
    }

    @Test
    public void testIsValidContractDocumentFalse() throws Throwable {
        final String typefulLine = "81770000000-0 01093659970-2 41131079703-9 00143370831-1";

        final boolean isValidContractDocument = BarcodeUtil.isValidContractDocument(typefulLine);
        assertFalse(isValidContractDocument);
    }

}
