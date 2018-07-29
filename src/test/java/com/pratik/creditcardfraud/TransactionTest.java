package com.pratik.creditcardfraud;

import com.pratik.creditcardfraud.entity.Transaction;
import org.junit.Test;

import static org.junit.Assert.*;

public class TransactionTest {

    @Test
    public void testParsing() {
        String csv = "9070aad3e7b3ec5594c9a84000057392,2018-06-08T05:18:02,829.58";
        Transaction transaction = new Transaction(csv);

        assertEquals("9070aad3e7b3ec5594c9a84000057392", transaction.getCardhash());
        assertEquals("2018-06-08T05:18:02", transaction.getTimestamp());
        assertEquals(829.58d, transaction.getPrice(), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBlankCsv() {
        new Transaction("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidCsv() {
        new Transaction("9070aad3e7b3ec5594c9a84000057392,,2018-06-08T05:18:02,829.58");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidPrice() {
        new Transaction("9070aad3e7b3ec5594c9a84000057392,2018-06-08T05:18:02,de45.65");
    }

    @Test
    public void testDayCheck() {
        assertTrue(new Transaction("9070aad3e7b3ec5594c9a84000057392,2018-06-08T05:18:02,829.58").doesDayMatch("2018-06-08"));
        assertTrue(new Transaction("9070aad3e7b3ec5594c9a84000057392,2018-06-08T00:00:00,829.58").doesDayMatch("2018-06-08"));
        assertTrue(new Transaction("9070aad3e7b3ec5594c9a84000057392,2018-06-08T23:59:59,829.58").doesDayMatch("2018-06-08"));

        assertFalse(new Transaction("9070aad3e7b3ec5594c9a84000057392,2018-06-08T05:18:02,829.58").doesDayMatch("2018-06-20"));
        assertFalse(new Transaction("9070aad3e7b3ec5594c9a84000057392,2018-06-08T00:00:00,829.58").doesDayMatch("2018-06-07"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativePrice() {
        new Transaction("9070aad3e7b3ec5594c9a84000057392,2018-06-08T05:18:02,-45.65");
    }
}
