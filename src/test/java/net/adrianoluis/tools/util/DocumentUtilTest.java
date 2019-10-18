package net.adrianoluis.tools.util;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DocumentUtilTest {

    @Test
    public void testValidSsn() throws Throwable {
        assertTrue(DocumentUtil.isValidSsn("34833028085"));
    }

    @Test
    public void testInvalidSsn() throws Throwable {
        assertFalse(DocumentUtil.isValidSsn("11111111111"));
    }

    @Test
    public void testValidTfn() throws Throwable {
        assertTrue(DocumentUtil.isValidTfn("08456242000104"));
    }

    @Test
    public void testInvalidTfn() throws Throwable {
        assertFalse(DocumentUtil.isValidTfn("11111111111111"));
    }
}
