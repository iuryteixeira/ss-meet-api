package com.ss.meet.meetapi.application.impl;

import java.util.ArrayList;
import java.util.List;

import com.ss.meet.meetapi.application.ScheduleProccessVoteApplication;
import com.ss.meet.meetapi.application.ScheduleSearchApplication;
import com.ss.meet.meetapi.application.SessionSearchApplication;
import com.ss.meet.meetapi.domain.schedule.Schedule;
import com.ss.meet.meetapi.domain.schedule.services.ScheduleSumResultService;
import com.ss.meet.meetapi.domain.vote.Vote;
import com.ss.meet.meetapi.infra.repository.ScheduleRepository;
import com.ss.meet.meetapi.infra.repository.VoteRepository;
import com.ss.meet.meetapi.infra.util.MeetLogger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class ScheduleProccessVoteApplicationImpl implements ScheduleProccessVoteApplication {
    private final MeetLogger logger = MeetLogger.instance(ScheduleProccessVoteApplicationImpl.class);
    private final ScheduleSearchApplication scheduleSearchApplication;
    private final SessionSearchApplication sessionSearchApplication;
    private final VoteRepository voteRepository;
    private final ScheduleSumResultService scheduleSumResultService;
    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleProccessVoteApplicationImpl(ScheduleSearchApplication scheduleSearchApplication,
            SessionSearchApplication sessionSearchApplication, VoteRepository voteRepository,
            ScheduleSumResultService scheduleSumResultService, ScheduleRepository scheduleRepository) {
        this.scheduleSearchApplication = scheduleSearchApplication;
        this.sessionSearchApplication = sessionSearchApplication;
        this.voteRepository = voteRepository;
        this.scheduleSumResultService = scheduleSumResultService;
        this.scheduleRepository = scheduleRepository;
    }

    /**
     * Execute the finish proccess votes by schedules
     */
    @Async
    @Override
    public void proccess() {
        try {
            final List<Schedule> finishedScheduleVotes = new ArrayList<>();
            scheduleSearchApplication.findByNoResult().forEach(s -> {
                logger.info("Schedules to proccess votes: {0}", s);
                sessionSearchApplication.findFinishedSessionsBySchedule(s).forEach(se -> {
                    final List<Vote> votes = voteRepository.findBySessionId(se.getId());
                    logger.info("Executing sum votes schedule: {0} session: {1} vote: {2}", s, se, votes);
                    compute(s, votes);
                    finishedScheduleVotes.add(s);
                    logger.info("Execution complete sum votes schedule: {0} session: {1} vote: {2}", s, se, votes);
                });
            });
            scheduleRepository.saveAll(finishedScheduleVotes);
        } catch (Exception e) {
            logger.error("Compute execution error", e);
        }
    }

    private void compute(final Schedule schedule, final List<Vote> votes) {
        scheduleSumResultService.execute(schedule, votes);
    }

}
