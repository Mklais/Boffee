package com.klaisapp.bookclub.exception;

import com.klaisapp.bookclub.common.messages.Message;
import lombok.Getter;

@Getter
public class CustomApplicationException extends RuntimeException {
    private final Message customMessage;

    public CustomApplicationException(Message customMessage) {
        super(customMessage.getContent());
        this.customMessage = customMessage;
    }
}
