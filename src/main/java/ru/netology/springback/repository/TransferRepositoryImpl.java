package ru.netology.springback.repository;

import ru.netology.springback.resources.Transfer;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class TransferRepositoryImpl implements TransferRepository {
    private final AtomicLong id=new AtomicLong(1);
    private final ConcurrentHashMap<Long, Transfer> repo = new ConcurrentHashMap<>();
    public Long addTransfer(Transfer transfer){
        Long transferId = id.getAndIncrement();
        transfer.setId(transferId);
        repo.put(transferId,transfer);
        return transferId;
    }
    public void refreshStatus(Long transferId,boolean status){
        Transfer transfer=repo.get(transferId);
        transfer.setStatus(status);
        repo.put(transferId,transfer);
    }

    public Transfer getTransfer(Long transferId){
        return repo.get(transferId);
    }
}
