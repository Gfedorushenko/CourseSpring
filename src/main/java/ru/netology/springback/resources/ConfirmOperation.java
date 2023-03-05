package ru.netology.springback.resources;

public class ConfirmOperation {
    private Long operationId;
    private String code;
    public ConfirmOperation(Long operationId, String code) {
        this.operationId = operationId;
        this.code = code;
    }
    public Long getOperationId() {
        return operationId;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "ConfirmOperation{" +
                "operationId=" + operationId +
                ", code='" + code + '\'' +
                '}';
    }
}
