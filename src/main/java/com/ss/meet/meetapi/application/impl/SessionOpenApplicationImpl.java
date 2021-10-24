package com.ss.meet.meetapi.application.impl;

import java.text.MessageFormat;
import java.time.LocalDateTime;

import com.ss.meet.meetapi.application.SessionOpenApplication;
import com.ss.meet.meetapi.domain.schedule.services.ScheduleContabilizeResultService;
import com.ss.meet.meetapi.domain.schedule.services.ScheduleSumResultService;
import com.ss.meet.meetapi.domain.session.Session;
import com.ss.meet.meetapi.infra.repository.SessionRepository;
import com.ss.meet.meetapi.infra.util.MeetException;
import com.ss.meet.meetapi.infra.util.MeetLogger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SessionOpenApplicationImpl implements SessionOpenApplication {
    
    private MeetLogger logger = MeetLogger.instance(SessionOpenApplicationImpl.class);

    @Autowired
    private ScheduleSumResultService scheduleSumResultService;
    @Autowired
    private ScheduleContabilizeResultService scheduleContabilizeResultService;

    @Autowired
    private SessionRepository sessionRepository;

    @Override
    public Session open(Long scheduleId) {
        return open(scheduleId, null);
    }

    @Override
    public Session open(Long scheduleId, Integer duration) {
        try {
            Session session = new Session(LocalDateTime.now(), duration);
            logger.info("Start in open session schedule {0} and duration", scheduleId, duration);
            return sessionRepository.save(session);
        } catch (Exception e) {
            logger.error("Error in open session schedule {0} and duration", e, scheduleId, duration);
            new MeetException(MessageFormat.format("Operation failed ", e.getMessage()));
        }
    }

}
