package com.ss.meet.meetapi.application.impl;

import com.ss.meet.meetapi.application.ScheduleRegisterApplication;
import com.ss.meet.meetapi.domain.schedule.Schedule;
import com.ss.meet.meetapi.domain.schedule.services.ScheduleFactory;
import com.ss.meet.meetapi.infra.repository.ScheduleRepository;
import com.ss.meet.meetapi.infra.util.MeetLogger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScheduleRegisterApplicationImpl implements ScheduleRegisterApplication {

    private MeetLogger logger = MeetLogger.instance(ScheduleRegisterApplicationImpl.class);
    @Autowired
    private ScheduleFactory scheduleFactory;
    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public Schedule register(final String subject) {
        final Schedule schedule = scheduleFactory.create(subject);
        logger.info("Before schedule create {0}", schedule);
        final Schedule scheduleSaved = scheduleRepository.save(schedule);
        logger.info("Successfully schedule created with code {0}", scheduleSaved.getId());
        return scheduleSaved;
    }

}
