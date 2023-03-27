package ru.netology.springback;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import ru.netology.springback.repository.CardRepository;
import ru.netology.springback.repository.TransferRepository;
import ru.netology.springback.resources.Amount;
import ru.netology.springback.resources.ConfirmOperation;
import ru.netology.springback.resources.Transfer;
import ru.netology.springback.service.TransferServiceImpl;

import static ru.netology.springback.resources.Currencies.RUR;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class TransferServiceTestMock {
    private final static Transfer transfer = new Transfer("2222222222222222",
            "12/34",
            "567",
            "1111111111111111",
            new Amount(5000, RUR));
    private final static ConfirmOperation confirmOperation = new ConfirmOperation(1L, "0000");
    @Mock
    private TransferRepository transferRepository;
    @Mock
    private CardRepository cardRepository;
    @InjectMocks
    private TransferServiceImpl transferService;

    @Test
    public void test_startTransfer() throws RuntimeException {
        Mockito.when(transferRepository.addTransfer(transfer)).thenReturn(2L);

        Mockito.when(cardRepository.cardExist(transfer.getCardFromNumber())).thenReturn(true);
        Mockito.when(cardRepository.cardExist(transfer.getCardToNumber())).thenReturn(true);
        Mockito.when(cardRepository.autification(transfer.getCard())).thenReturn(true);
        Mockito.when(cardRepository.cardOperation("2222222222222222", new Amount(-5050, RUR))).thenReturn(true);
        Mockito.when(cardRepository.cardOperation("1111111111111111", new Amount(5000, RUR))).thenReturn(true);

        Long result = transferService.startTransfer(transfer);
        Assertions.assertEquals(2L, result);
    }

    @Test
    public void test_startConfirmOperation() throws RuntimeException {
        Long result = transferService.startConfirmOperation(confirmOperation);
        Assertions.assertEquals(1L, result);
    }

    @Test
    public void test_transferOperation() throws RuntimeException {
        Mockito.when(cardRepository.cardOperation("2222222222222222", new Amount(-5050, RUR))).thenReturn(true);
        Mockito.when(cardRepository.cardOperation("1111111111111111", new Amount(5000, RUR))).thenReturn(true);

        boolean result = transferService.transferOperation(transfer);
        Assertions.assertEquals(true, result);
    }
}
