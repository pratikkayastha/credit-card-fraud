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

    public static Set<String> detectFraud(List<String> transactions, Date checkDate,
                              double threshold) {
        Set<String> fraudHashs = new HashSet<String>();

        if (transactions.isEmpty()) {
            return fraudHashs;
        }

        Map<String, Double> cardTotal = new HashMap<String, Double>();
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String checkDay = dateFormat.format(checkDate);

        for (String trans : transactions) {
            Transaction eachTrans = new Transaction(trans);

            if (eachTrans.doesDayMatch(checkDay) && !fraudHashs.contains(eachTrans.getCardhash())) {
                Double totalPrice = cardTotal.containsKey(eachTrans.getCardhash()) ? cardTotal.get(eachTrans.getCardhash()) : 0;
                totalPrice = totalPrice + eachTrans.getPrice();

                cardTotal.put(eachTrans.getCardhash(), totalPrice);

                if (totalPrice >= threshold) {
                    fraudHashs.add(eachTrans.getCardhash());
                }
            }
        }

        return fraudHashs;
    }
}
