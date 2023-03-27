package ru.netology.springback.service;

import ru.netology.springback.exception.DataTransferError;
import ru.netology.springback.exception.OperationError;
import ru.netology.springback.repository.CardRepository;
import ru.netology.springback.repository.TransferRepository;
import ru.netology.springback.resources.Amount;
import ru.netology.springback.resources.ConfirmOperation;
import ru.netology.springback.resources.Transfer;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TransferServiceImpl implements TransferService{

    private final CardRepository cardRepository;
    private final TransferRepository transferRepository;

    private static final Logger logger = Logger.getLogger("outFile.txt");

    public TransferServiceImpl(CardRepository cardRepository, TransferRepository transferRepository) {
        this.cardRepository = cardRepository;
        this.transferRepository = transferRepository;
    }

    public Long startTransfer(Transfer transfer) throws RuntimeException {
        Long transferId = transferRepository.addTransfer(transfer);
        String msg;
        if (!cardRepository.cardExist(transfer.getCardToNumber())){
            msg="Карта для пополнения с номером " + transfer.getCardToNumber() + " отсутсвует";
            logger.log(Level.WARNING,msg);
            throw new DataTransferError(msg);
        }

        if (!cardRepository.cardExist(transfer.getCardFromNumber())){
            msg="Карта для списания с номером " + transfer.getCardFromNumber() + " отсутсвует";
            logger.log(Level.WARNING,msg);
            throw new DataTransferError(msg);
        }

        if (!cardRepository.autification(transfer.getCard())){
            msg="Ошибка ввода года/месяца или SVV";
            logger.log(Level.WARNING,msg);
            throw new DataTransferError(msg);
        }

        if (!transferOperation(transfer)){
            msg="Недостаточно средств для перевода";
            logger.log(Level.WARNING,msg);
            throw new OperationError(msg);
        }

        transferRepository.refreshStatus(transferId, true);
        msg=transfer.toString();
        logger.log(Level.INFO,msg);
        return transferId;
    }

    public Long startConfirmOperation(ConfirmOperation ConfirmOperation) throws RuntimeException {

        Long transferId = ConfirmOperation.getOperationId();
        if (!ConfirmOperation.getCode().equals("0000"))
            throw new DataTransferError("Код подтверждения " + ConfirmOperation.getCode() + " неправильный");

        return transferId;
    }


    public boolean transferOperation(Transfer transfer) {
        int value = transfer.getAmount().getValue();

        boolean rez= cardRepository.cardOperation(transfer.getCardFromNumber(), new Amount((int) (-value * 1.01), transfer.getAmount().getCurrency()));
        boolean rez2=cardRepository.cardOperation(transfer.getCardToNumber(), new Amount(value, transfer.getAmount().getCurrency()));

        return cardRepository.cardOperation(transfer.getCardFromNumber(), new Amount((int) (-value * 1.01), transfer.getAmount().getCurrency())) &&
                cardRepository.cardOperation(transfer.getCardToNumber(), new Amount(value, transfer.getAmount().getCurrency()));
    }
}
