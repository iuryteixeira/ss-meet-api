package com.ss.meet.meetapi.application.impl;

import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ss.meet.meetapi.application.SessionSearchApplication;
import com.ss.meet.meetapi.application.VoteReceivedApplication;
import com.ss.meet.meetapi.domain.session.Session;
import com.ss.meet.meetapi.domain.session.services.SessionOpenVerifyService;
import com.ss.meet.meetapi.domain.vote.Vote;
import com.ss.meet.meetapi.domain.vote.VoteValue;
import com.ss.meet.meetapi.infra.messaging.producer.FinishSessionProducer;
import com.ss.meet.meetapi.infra.messaging.producer.FinishVoteDataTransfer;
import com.ss.meet.meetapi.infra.repository.VoteRepository;
import com.ss.meet.meetapi.infra.util.MeetException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteReceivedApplicationImpl implements VoteReceivedApplication {

    @Autowired
    private FinishSessionProducer finishSessionProducer;
    @Autowired
    private SessionOpenVerifyService openVerifyService;
    @Autowired
    private SessionSearchApplication sessionSearchApplication;
    @Autowired
    private VoteRepository voteRepository;

    @Override
    public void receive(Long sessionId, String vote, String userId) {
        final Session session = sessionSearchApplication.findById(sessionId);
        openVerifyService.verify(session);
        if (Optional.ofNullable(voteRepository.findByUserId(userId)).isPresent())
            throw new MeetException("User has already voted in this session", 409);

        voteRepository.save(new Vote(session, userId, VoteValue.valueOf(vote)));
        finishSessionProducer.publish(new FinishVoteDataTransfer(userId, sessionId.toString(), vote));
    }
}
