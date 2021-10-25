package com.ss.meet.meetapi.infra.messaging.config;

import java.io.Serializable;

public class VoteDataTransfer implements Serializable {

    private String userId;

    private String vote;

    private String sessionId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public String toString() {
        return "DataTransfer [sessionId=" + sessionId + ", userId=" + userId + ", vote=" + vote + "]";
    }

}
