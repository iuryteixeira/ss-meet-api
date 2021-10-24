package com.ss.meet.meetapi.application.impl;

import java.text.MessageFormat;
import java.util.Optional;

import com.ss.meet.meetapi.application.ScheduleSearchApplication;
import com.ss.meet.meetapi.application.SessionOpenApplication;
import com.ss.meet.meetapi.domain.schedule.Schedule;
import com.ss.meet.meetapi.domain.session.Session;
import com.ss.meet.meetapi.domain.session.services.SessionFactory;
import com.ss.meet.meetapi.infra.repository.SessionRepository;
import com.ss.meet.meetapi.infra.util.MeetException;
import com.ss.meet.meetapi.infra.util.MeetLogger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

@Component
@ApplicationScope
public class SessionOpenApplicationImpl implements SessionOpenApplication {

    private final MeetLogger logger = MeetLogger.instance(SessionOpenApplicationImpl.class);

    private final SessionFactory sessionFactory;

    private final ScheduleSearchApplication scheduleSearchApplication;
    private final SessionRepository sessionRepository;

    @Autowired
    public SessionOpenApplicationImpl(SessionFactory sessionFactory,
            ScheduleSearchApplication scheduleSearchApplication, SessionRepository sessionRepository) {
        this.sessionFactory = sessionFactory;
        this.scheduleSearchApplication = scheduleSearchApplication;
        this.sessionRepository = sessionRepository;
    }

    @Override
    public Session open(Long scheduleId, Integer duration) {

        // find schedule if exists
        final Schedule schedule = scheduleSearchApplication.findById(
                Optional.ofNullable(scheduleId).orElseThrow(() -> new MeetException("Schedule Id not found", 400)));

        // create a session if not exist session for this schedule
        if (!sessionRepository.findByScheduleId(scheduleId).isPresent()) {
            final Session session = sessionFactory.create(schedule, duration);
            logger.info("Open session created after save {0}", schedule);
            return sessionRepository.save(session);
        }
        logger.info("There is already a session for schedule {0}", scheduleId, duration);
        throw new MeetException(MessageFormat.format("There is already a session for schedule {0} ", scheduleId), 409);
    }

}
