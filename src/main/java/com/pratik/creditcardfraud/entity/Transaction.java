package com.pratik.creditcardfraud.entity;

public class Transaction {

    private String cardhash;
    private double price;
    private String timestamp;

    public Transaction (String rawTransaction) {
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

        this.cardhash = transactionDetails[0];
        this.timestamp = transactionDetails[1];

        try {
            this.price = Double.parseDouble(transactionDetails[2]);
        } catch (NumberFormatException nfe) {
            throw new IllegalArgumentException("Price is not in proper format!");
        }
        if (this.price<0) {
            throw new IllegalArgumentException("Price cannot be less than zero!");
        }
    }

    public boolean doesDayMatch(String checkDay) {
        return this.timestamp.contains(checkDay);
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
