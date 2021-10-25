package com.ss.meet.meetapi.infra.jobs;

import com.ss.meet.meetapi.application.ScheduleProccessVoteApplication;
import com.ss.meet.meetapi.infra.util.MeetLogger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * ScheduleProccessVoteJob
 */
@Component
public class ScheduleProccessVoteJob {
    private final MeetLogger logger = MeetLogger.instance(ScheduleProccessVoteJob.class);
    private ScheduleProccessVoteApplication proccessVoteApplication;

    @Autowired
    public ScheduleProccessVoteJob(ScheduleProccessVoteApplication proccessVoteApplication) {
        this.proccessVoteApplication = proccessVoteApplication;
    }

    @Scheduled(fixedRate = 60000)
    public void proccessVotes() {
        logger.info("Start proccess votes");
        proccessVoteApplication.proccess();
    }

}