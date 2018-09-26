package com.epam.lab.currency.type;

public enum Currency {
    UAH('\u20B4', "UAH"), USD('\u0024', "USD"), EUR('\u20ac', "EUR"), GBP('\u00a3', "GBP");

    private char symbol;
    private String currencyStr;

    Currency(char symbol, String currencyStr) {
        this.symbol = symbol;
        this.currencyStr = currencyStr;
    }

    public char getSymbol() {
        return symbol;
    }

    public String getCurrencyStr() {
        return currencyStr;
    }
}