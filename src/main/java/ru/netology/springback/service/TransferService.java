package ru.netology.springback.service;

import ru.netology.springback.exception.DataTransferError;
import ru.netology.springback.exception.OperationError;
import ru.netology.springback.repository.CardRepository;
import ru.netology.springback.repository.TransferRepository;
import ru.netology.springback.resources.Amount;
import ru.netology.springback.resources.ConfirmOperation;
import ru.netology.springback.resources.Transfer;

public class TransferService {
    private CardRepository cardRepository;
    private TransferRepository transferRepository;

    public TransferService(CardRepository cardRepository, TransferRepository transferRepository) {
        this.cardRepository = cardRepository;
        this.transferRepository = transferRepository;
    }

    public Long startTransfer(Transfer transfer) throws RuntimeException {
        Long transferId = transferRepository.addTransfer(transfer);

        if (!cardRepository.cardExist(transfer.getCardToNumber()))
            throw new DataTransferError("Карта для пополнения с номером " + transfer.getCardToNumber() + " отсутсвует");

        if (!cardRepository.cardExist(transfer.getCardFromNumber()))
            throw new DataTransferError("Карта для списания с номером " + transfer.getCardToNumber() + " отсутсвует");

        if (!cardRepository.autification(transfer.getCard()))
            throw new DataTransferError("Ошибка ввода года/месяца или SVV");

        if (!transferOperation(transfer))
            throw new OperationError("Недостаточно средств для перевода");

        transferRepository.refreshStatus(transferId, true);
        return transferId;
    }

    public Long startСonfirmOperation(ConfirmOperation ConfirmOperation) throws RuntimeException {

        Long transferId = ConfirmOperation.getOperationId();
        if (!ConfirmOperation.getCode().equals("0000"))
            throw new DataTransferError("Код подтверждения " + ConfirmOperation.getCode() + " неправильный");

        return transferId;
    }


    public boolean transferOperation(Transfer transfer) {
        int value = transfer.getAmount().getValue();
        return cardRepository.cardOperation(transfer.getCardFromNumber(), new Amount((int) (-value * 1.01), transfer.getAmount().getCurrency())) &&
                cardRepository.cardOperation(transfer.getCardToNumber(), new Amount(value, transfer.getAmount().getCurrency()));
    }
}
