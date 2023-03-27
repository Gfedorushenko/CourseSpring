package ru.netology.springback.service;

import ru.netology.springback.resources.ConfirmOperation;
import ru.netology.springback.resources.Transfer;

public interface TransferService {
    Long startTransfer(Transfer transfer) throws RuntimeException;
    Long startConfirmOperation(ConfirmOperation ConfirmOperation) throws RuntimeException;
    boolean transferOperation(Transfer transfer);
}
