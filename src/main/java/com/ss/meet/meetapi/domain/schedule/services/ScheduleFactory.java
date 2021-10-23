package com.ss.meet.meetapi.domain.schedule.services;

import java.util.Optional;

import com.ss.meet.meetapi.domain.schedule.Schedule;
import com.ss.meet.meetapi.infra.util.MeetException;

import org.springframework.stereotype.Service;

@Service
public class ScheduleFactory {

    public Schedule create(String subject) {
        return new Schedule(
                Optional.ofNullable(subject).orElseThrow(() -> new MeetException("Invalid subject to schedule", 400)));

    }

}
