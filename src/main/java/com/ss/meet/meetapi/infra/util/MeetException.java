package com.ss.meet.meetapi.infra.util;

import java.util.List;
import java.util.Optional;

/**
 * Application exception
 */
public class MeetException extends RuntimeException {

    /**
    *
    */
    private static final long serialVersionUID = 1L;

    private List<String> messages;
    private Integer statusCode;

    public MeetException(String message) {
        super(message);
        this.messages = List.of(message);
    }

    public MeetException(String message, int code) {
        super(message);
        this.messages = List.of(message);
        this.statusCode = code;
    }

    public MeetException(List<String> messages) {
        super(messages.toString());
        this.messages = messages;
    }

    public List<String> getMessages() {
        return messages;
    }

    public Integer getStatusCode() {
        return Optional.ofNullable(statusCode).orElse(400);
    }

}
