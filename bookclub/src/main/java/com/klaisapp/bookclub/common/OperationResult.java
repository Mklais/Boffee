package com.klaisapp.bookclub.common;

import lombok.Data;

@Data
public class OperationResult {
    private boolean success;
    private String message;
    private Object data;

    public static OperationResult success(String message, Object data) {
        OperationResult result = new OperationResult();
        result.setSuccess(true);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    public static OperationResult failure(String message) {
        OperationResult result = new OperationResult();
        result.setSuccess(false);
        result.setMessage(message);
        return result;
    }
}
