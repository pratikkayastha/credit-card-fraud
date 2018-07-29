package com.pratik.creditcardfraud;

import com.pratik.creditcardfraud.helper.FileToListReader;

import java.util.*;

/**
 * Driver Class
 * Reads transaction from transactions.csv and passes it to FraudDetector
 * */
public class CreditCardFraud {

    public static void main(String[] args) {
        // Date: 2018-06-15
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2018);
        calendar.set(Calendar.MONTH, Calendar.JUNE);
        calendar.set(Calendar.DAY_OF_MONTH, 15);

        Set<String> fraudHashes = FraudDetector.detectFraud(FileToListReader.read("transactions.csv"),
                                        calendar.getTime(), 1000);

        System.out.println(fraudHashes.size());
        for (String fraudHash : fraudHashes) {
            System.out.println(fraudHash);
        }
    }
}
