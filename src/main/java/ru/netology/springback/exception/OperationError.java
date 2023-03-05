package ru.netology.springback.exception;

public class OperationError extends RuntimeException {
    public OperationError(String msg) {
        super(msg);
    }
}
