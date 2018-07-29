package com.pratik.creditcardfraud;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

public class FraudDetectorTest {

    @Test
    public void testParsingAndDateFiltering() {
        List<String> rawTransactions = Arrays.asList(
                "apple,2018-06-08T05:18:02,89.58",
                "banana,2018-06-08T06:39:59,51.04",
                "cat,2018-06-22T06:50:15,469.58",
                "apple,2018-06-08T06:39:59,34.04",
                "apple,2018-06-16T06:39:59,12.04",
                "cat,2018-06-08T06:39:59,551.04",
                "apple,2018-06-08T06:39:59,89.04",
                "dog,2018-06-16T06:39:59,12.04"
        );

        // Date: 2018-06-08
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2018);
        calendar.set(Calendar.MONTH, Calendar.JUNE);
        calendar.set(Calendar.DAY_OF_MONTH, 8);

        Set<String> frauds = FraudDetector.detectFraud(rawTransactions,
                                            calendar.getTime(), 180);

        assertEquals(2, frauds.size());
        assertTrue(frauds.contains("apple") && frauds.contains("cat"));
        assertEquals(FraudDetector.detectFraud(rawTransactions, calendar.getTime(), 1800).size(), 0);

        // Date: 2015-02-10
        calendar.set(Calendar.YEAR, 2015);
        calendar.set(Calendar.MONTH, Calendar.FEBRUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 10);
        assertEquals(FraudDetector.detectFraud(rawTransactions, calendar.getTime(), 180).size(), 0);
    }
}