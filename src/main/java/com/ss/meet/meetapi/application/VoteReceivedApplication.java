package com.ss.meet.meetapi.application;

public interface VoteReceivedApplication {
    void receive(Long sessionId, String vote, String userId);
}
