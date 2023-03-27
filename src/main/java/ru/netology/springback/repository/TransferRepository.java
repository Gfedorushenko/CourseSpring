package ru.netology.springback.repository;

import ru.netology.springback.resources.Transfer;

public interface TransferRepository {
    Long addTransfer(Transfer transfer);

    void refreshStatus(Long transferId,boolean status);

    Transfer getTransfer(Long transferId);
}
