package com.klaisapp.bookclub.common.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Message {
    private final MessageType type;
    private final String content;

    public static Message error(String content) {
        return new Message(MessageType.ERROR, content);
    }
    public static Message success(String content) {
        return new Message(MessageType.SUCCESS, content);
    }
    public static Message info(String content) {
        return new Message(MessageType.INFO, content);
    }
}
