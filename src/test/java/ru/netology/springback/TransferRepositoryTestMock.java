package ru.netology.springback;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import ru.netology.springback.repository.TransferRepositoryImpl;
import ru.netology.springback.resources.Amount;
import ru.netology.springback.resources.Transfer;

import static ru.netology.springback.resources.Currencies.RUR;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class TransferRepositoryTestMock {

    @InjectMocks
    private TransferRepositoryImpl transferRepository;

    @BeforeEach
    public void setUp() {
        Long result = transferRepository.addTransfer(new Transfer("1234567890123457",
                "12/37",
                "127",
                "2222222222222227",
                new Amount(-200_00, RUR)));
    }

    @Test
    public void test_addTransfer() {
        Transfer transfer = new Transfer("1234567890123456",
                "12/34",
                "123",
                "2222222222222222",
                new Amount(-200_00, RUR));
        Long result = transferRepository.addTransfer(transfer);
        Assertions.assertEquals(2L, result);
    }

    @Test
    public void test_refreshStatus() {
        Long transferId = 1L;
        transferRepository.refreshStatus(transferId, true);
        Transfer transfer = transferRepository.getTransfer(transferId);
        Assertions.assertTrue(transfer.getStatus());
    }
}
