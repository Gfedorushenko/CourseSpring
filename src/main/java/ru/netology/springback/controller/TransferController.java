package ru.netology.springback.controller;

import org.springframework.web.bind.annotation.*;
import ru.netology.springback.service.TransferService;
import ru.netology.springback.resources.ConfirmOperation;
import ru.netology.springback.resources.Transfer;

@RestController
@CrossOrigin
public class TransferController {
    private TransferService service;

    public TransferController(TransferService service) {
        this.service = service;
    }

    @PostMapping("/transfer")
    public Long getTransfer(@RequestBody Transfer transfer)
            throws RuntimeException {
        return service.startTransfer(transfer);
    }

    @PostMapping("/confirmOperation")
    public Long getConfirmOperation(@RequestBody ConfirmOperation confirmOperation)
            throws RuntimeException {
        return service.startСonfirmOperation(confirmOperation);
    }
}
