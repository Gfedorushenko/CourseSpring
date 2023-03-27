package ru.netology.springback.repository;

import ru.netology.springback.resources.Amount;
import ru.netology.springback.resources.Card;

public interface CardRepository {
    boolean cardExist(String number);

    boolean autification(Card card);

    boolean cardOperation(String cardNumber, Amount amount);

    boolean addCard(Card card);
}
