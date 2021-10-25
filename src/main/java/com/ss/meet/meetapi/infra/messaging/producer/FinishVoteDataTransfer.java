package com.ss.meet.meetapi.infra.messaging.producer;

import java.io.Serializable;

public class FinishVoteDataTransfer implements Comparable<FinishVoteDataTransfer> {

    private String userId;
    private String sessionId;
    private String vote;

    public FinishVoteDataTransfer() {
    }

    public FinishVoteDataTransfer(String userId, String sessionId, String vote) {
        this.userId = userId;
        this.sessionId = sessionId;
        this.vote = vote;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }

    @Override
    public int compareTo(FinishVoteDataTransfer o) {
        return getUserId().compareTo(o.getUserId());
    }

    @Override
    public String toString() {
        return "FinishVoteDataTransfer [sessionId=" + sessionId + ", userId=" + userId + ", vote=" + vote + "]";
    }

}
