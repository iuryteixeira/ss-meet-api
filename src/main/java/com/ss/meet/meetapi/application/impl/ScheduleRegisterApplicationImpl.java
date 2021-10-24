package com.ss.meet.meetapi.application.impl;

import java.text.MessageFormat;

import com.ss.meet.meetapi.application.ScheduleRegisterApplication;
import com.ss.meet.meetapi.domain.schedule.Schedule;
import com.ss.meet.meetapi.domain.schedule.services.ScheduleFactory;
import com.ss.meet.meetapi.infra.repository.ScheduleRepository;
import com.ss.meet.meetapi.infra.util.MeetException;
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
        Schedule schedule = scheduleFactory.create(subject);
        try {
            logger.info("Before schedule create {0}", schedule);
            schedule = scheduleRepository.save(schedule);
            logger.info("Successfully schedule created with code {0}", schedule.getId());
        } catch (Exception e) {
            logger.error("Error in create schedule {0}", e, schedule);
            new MeetException(MessageFormat.format("Operation failed ", e.getMessage()));
        }
        return schedule;
    }

}
