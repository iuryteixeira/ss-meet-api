package com.ss.meet.meetapi.infra.util;

import java.util.List;

/**
 * Application exception
 */
public class MeetException extends RuntimeException {

    /**
    *
    */
    private static final long serialVersionUID = 1L;

    private List<String> messages;

    public MeetException(String message) {
        super(message);
        this.messages = List.of(message);
    }

    public MeetException(List<String> messages) {
        super(messages.toString());
        this.messages = messages;
    }

    public List<String> getMessages() {
        return messages;
    }

}
