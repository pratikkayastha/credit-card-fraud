package com.pratik.creditcardfraud.entity;

public class Transaction {

    private String cardhash;
    private double price;
    private String timestamp;

    public boolean doesDayMatch(String checkDay) {
        return this.timestamp.contains(checkDay);
    }

    public static Transaction parse(String rawTransaction) {
        Transaction transaction = new Transaction();

        if (rawTransaction==null) {
            throw new IllegalArgumentException("Transaction CSV is not in proper format!");
        }
        if (rawTransaction.length()==0) {
            throw new IllegalArgumentException("Transaction CSV is not in proper format!");
        }

        String[] transactionDetails = rawTransaction.split(",");
        if (transactionDetails.length!=3) {
            throw new IllegalArgumentException("Transaction CSV is not in proper format!");
        }

        transaction.setCardhash(transactionDetails[0]);
        transaction.setTimestamp(transactionDetails[1]);

        try {
            transaction.setPrice(Double.parseDouble(transactionDetails[2]));
        } catch (NumberFormatException nfe) {
            throw new IllegalArgumentException("Price is not in proper format!");
        }
        if (transaction.getPrice()<0) {
            throw new IllegalArgumentException("Price cannot be less than zero!");
        }

        return transaction;

    }

    public String getCardhash() {
        return cardhash;
    }

    public void setCardhash(String cardhash) {
        this.cardhash = cardhash;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
