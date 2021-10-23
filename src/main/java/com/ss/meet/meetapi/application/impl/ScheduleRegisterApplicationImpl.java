package com.ss.meet.meetapi.application.impl;

import java.util.Optional;

import com.ss.meet.meetapi.application.ScheduleRegisterApplication;
import com.ss.meet.meetapi.domain.schedule.Schedule;
import com.ss.meet.meetapi.domain.schedule.services.ScheduleContabilizeResultService;
import com.ss.meet.meetapi.domain.schedule.services.ScheduleSumResultService;
import com.ss.meet.meetapi.infra.repository.ScheduleRepository;
import com.ss.meet.meetapi.infra.util.MeetException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScheduleRegisterApplicationImpl implements ScheduleRegisterApplication {

    @Autowired
    private ScheduleSumResultService scheduleSumResultService;
    @Autowired
    private ScheduleContabilizeResultService scheduleContabilizeResultService;
    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public Schedule register(final String subject) {
        final Schedule schedule = new Schedule(
                Optional.ofNullable(subject).orElseThrow(() -> new MeetException("Invalid subject to schedule")));
        return scheduleRepository.save(schedule);
    }

}
