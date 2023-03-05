package ru.netology.springback.repository;

import ru.netology.springback.resources.Amount;
import ru.netology.springback.resources.Card;

import java.util.concurrent.ConcurrentHashMap;

import static ru.netology.springback.resources.Currencies.*;

public class CardRepository {
    private final ConcurrentHashMap<String, Card> repo = new ConcurrentHashMap<>();

    public CardRepository() {
        Card card1 = new Card("1111111111111111", "111", "11/11");
        card1.cardOperation(new Amount(100_000_00, RUR));
        card1.cardOperation(new Amount(1000_00, EUR));
        repo.put(card1.getNumber(), card1);

        Card card2 = new Card("2222222222222222", "567", "12/34");
        card2.cardOperation(new Amount(100_00, RUR));
        repo.put(card2.getNumber(), card2);

        Card card3 = new Card("1234567890123456", "777", "19/96");
        repo.put(card3.getNumber(), card3);
    }

    public boolean cardExist(String number) {
        return repo.containsKey(number);
    }

    public boolean autification(Card card) {
        if (cardExist(card.getNumber()))
            return repo.get(card.getNumber()).equals(card);
        return false;
    }

    public boolean cardOperation(String cardNumber, Amount amount) {
        Card card = repo.get(cardNumber);
        return card.cardOperation(amount);
    }
}
