package ru.netology.springback.resources;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Amount amount)) return false;
        return value == amount.value && currency == amount.currency;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, currency);
    }

    @Override
    public String toString() {
        return "Amount{" +
                "value=" + value +
                ", currency='" + currency + '\'' +
                '}';
    }
}
