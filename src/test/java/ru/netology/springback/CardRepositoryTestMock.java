package ru.netology.springback;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import ru.netology.springback.repository.CardRepositoryImpl;
import ru.netology.springback.resources.Amount;
import ru.netology.springback.resources.Card;

import java.util.stream.Stream;

import static ru.netology.springback.resources.Currencies.RUR;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class CardRepositoryTestMock {
    @InjectMocks
    private CardRepositoryImpl cardRepository;

    @ParameterizedTest
    @MethodSource("source1")
    void test_cardExist(boolean expected, String number) {
        boolean result = cardRepository.cardExist(number);
        Assertions.assertEquals(expected, result);
    }

    private static Stream<Arguments> source1() {
        return Stream.of(
                Arguments.of(true, "1234567890123456"),
                Arguments.of(false, "1234567890123459")
        );
    }

    @ParameterizedTest
    @MethodSource("source2")
    void test_autification(boolean expected, Card card) {
        boolean result = cardRepository.autification(card);
        Assertions.assertEquals(expected, result);
    }

    private static Stream<Arguments> source2() {
        return Stream.of(
                Arguments.of(true, new Card("2222222222222222", "567", "12/34")),
                Arguments.of(false, new Card("2222222222222223", "567", "12/34"))
        );
    }

    @ParameterizedTest
    @MethodSource("source3")
    void test_cardOperation(boolean expected, String cardNumber, Amount amount) {
        boolean result = cardRepository.cardOperation(cardNumber, amount);
        Assertions.assertEquals(expected, result);
    }

    private static Stream<Arguments> source3() {
        return Stream.of(
                Arguments.of(true, "2222222222222222", new Amount(-99_00, RUR)),
                Arguments.of(false, "2222222222222222", new Amount(-200_00, RUR))
        );
    }

    @Test
    void test_addCard() {
        Card card = new Card("2222222222222222", "567", "12/34");
        Assertions.assertFalse(cardRepository.addCard(card));
    }
}
