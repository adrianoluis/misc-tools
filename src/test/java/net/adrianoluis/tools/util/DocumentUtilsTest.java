package net.adrianoluis.tools.util;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DocumentUtilsTest {

    @Test
    public void testValidSsn() throws Throwable {
        assertTrue(DocumentUtils.isValidSsn("34833028085"));
    }

    @Test
    public void testInvalidSsn() throws Throwable {
        assertFalse(DocumentUtils.isValidSsn(null));
        assertFalse(DocumentUtils.isValidSsn("1234567890"));
        assertFalse(DocumentUtils.isValidSsn("11111111111"));
    }

    @Test
    public void testValidTfn() throws Throwable {
        assertTrue(DocumentUtils.isValidTfn("08456242000104"));
    }

    @Test
    public void testInvalidTfn() throws Throwable {
        assertFalse(DocumentUtils.isValidTfn(null));
        assertFalse(DocumentUtils.isValidTfn("1234567890"));
        assertFalse(DocumentUtils.isValidTfn("11111111111111"));
    }
}
