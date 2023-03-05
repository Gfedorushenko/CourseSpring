package ru.netology.springback.exception;

public class DataTransferError extends RuntimeException {
    public DataTransferError(String msg) {
        super(msg);
    }
}