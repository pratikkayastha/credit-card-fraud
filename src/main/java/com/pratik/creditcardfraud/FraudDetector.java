package com.pratik.creditcardfraud;

import com.pratik.creditcardfraud.entity.Transaction;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * This is how it works
 * 1. Parse each transaction and check if the date is same as check date
 * 2. If the date is same as check date and hash is not already marked as fraud, continue, otherwise go to next iteration
 * 3. Maintain hashmap of hashes and their total price
 * 4. If total price is greater than threshold, add hash to fraudHashs set; since its a Set, hashes will be unique
 */
public class FraudDetector {

    public static Set<String> detectFraud(List<String> transactionList, Date checkDate,
                              double threshold) {
        Set<String> fraudHashs = new HashSet<String>();

        if (transactionList.isEmpty()) {
            return fraudHashs;
        }

        Map<String, Double> cardsTotals = new HashMap<String, Double>();
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String checkDay = dateFormat.format(checkDate);

        for (String rawTransaction : transactionList) {
            Transaction transaction = new Transaction(rawTransaction);

            if (transaction.doesDayMatch(checkDay) && !fraudHashs.contains(transaction.getCardhash())) {
                Double sum = cardsTotals.containsKey(transaction.getCardhash()) ? cardsTotals.get(transaction.getCardhash()) : 0;
                sum = sum + transaction.getPrice();

                cardsTotals.put(transaction.getCardhash(), sum);

                if (sum >= threshold) {
                    fraudHashs.add(transaction.getCardhash());
                }
            }
        }

        return fraudHashs;
    }
}
