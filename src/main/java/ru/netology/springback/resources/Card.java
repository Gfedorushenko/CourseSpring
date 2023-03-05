package ru.netology.springback.resources;

import java.util.HashMap;
import java.util.Objects;


public class Card {
    private String number;
    private String svv;
    private String cardFromValidTill;
    private HashMap<Currencies, Integer> currencies=new HashMap<>();
    public String getNumber() {
        return number;
    }

    public Card(String number, String svv, String cardFromValidTill) {
        this.number = number;
        this.svv = svv;
        this.cardFromValidTill = cardFromValidTill;
    }

    public Card(String number, String svv, String cardFromValidTill, HashMap<Currencies, Integer> currencies) {
        this.number = number;
        this.svv = svv;
        this.cardFromValidTill = cardFromValidTill;
        this.currencies = currencies;
    }

    public boolean cardOperation(Amount amount) {
        int summ = amount.getValue();

        if (currencies.containsKey(amount.getCurrency()))
            summ += currencies.get(amount.getCurrency());

        if (summ > 0) {
            currencies.put(amount.getCurrency(), summ);
            return true;
        } else
            return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card card)) return false;
        return number.equals(card.number) && svv.equals(card.svv) && cardFromValidTill.equals(card.cardFromValidTill);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, svv, cardFromValidTill);
    }
}
