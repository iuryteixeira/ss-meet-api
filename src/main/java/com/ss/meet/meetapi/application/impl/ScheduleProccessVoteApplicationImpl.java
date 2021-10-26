package com.ss.meet.meetapi.application.impl;

import com.ss.meet.meetapi.application.ScheduleProccessVoteApplication;
import com.ss.meet.meetapi.application.ScheduleSearchApplication;
import com.ss.meet.meetapi.application.SessionSearchApplication;
import com.ss.meet.meetapi.domain.vote.Vote;
import com.ss.meet.meetapi.infra.repository.VoteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class ScheduleProccessVoteApplicationImpl implements ScheduleProccessVoteApplication {

    private final ScheduleSearchApplication scheduleSearchApplication;
    private final SessionSearchApplication sessionSearchApplication;
    private final VoteRepository voteRepository;

    @Autowired
    public ScheduleProccessVoteApplicationImpl(ScheduleSearchApplication scheduleSearchApplication,
            SessionSearchApplication sessionSearchApplication, VoteRepository voteRepository) {
        this.scheduleSearchApplication = scheduleSearchApplication;
        this.sessionSearchApplication = sessionSearchApplication;
        this.voteRepository = voteRepository;
    }

    @Async
    @Override
    public void proccess() {
        scheduleSearchApplication.findByNoResult().forEach(s -> {
            sessionSearchApplication.findFinishedSessionsBySchedule(s).forEach(se -> {
                voteRepository.findBySessionId(se.getId());
            });
        });
    }

    private void sum(Vote vote){
        
    }


    


}
