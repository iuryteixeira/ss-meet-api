package com.ss.meet.meetapi.presentation.vote.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class VoteCreateDTO implements Serializable {

    @NotBlank(message = "Vote is required")
    @Pattern(regexp = "YES|NO")
    private String value;

    @NotBlank(message = "User id is required")
    private String userId;

    @NotNull(message = "Session id is required")
    private Long sessionId;

    public String getValue() {
        return value;
    }

    public VoteCreateDTO setValue(String value) {
        this.value = value;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public VoteCreateDTO setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public VoteCreateDTO setSessionId(Long sessionId) {
        this.sessionId = sessionId;
        return this;
    }

    @Override
    public String toString() {
        return "VoteCreateDTO [sessionId=" + sessionId + ", userId=" + userId + ", value=" + value + "]";
    }

}
