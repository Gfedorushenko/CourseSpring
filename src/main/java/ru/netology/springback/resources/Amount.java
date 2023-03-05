package ru.netology.springback.resources;

public class Amount {
    private int value;
    private Currencies currency;

    public Amount(int value, Currencies currency) {
        this.value = value;
        this.currency = currency;
    }
    public int getValue() {
        return value;
    }
    public Currencies getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return "Amount{" +
                "value=" + value +
                ", currency='" + currency + '\'' +
                '}';
    }
}
