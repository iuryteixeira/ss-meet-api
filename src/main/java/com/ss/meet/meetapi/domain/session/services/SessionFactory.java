package com.ss.meet.meetapi.domain.session.services;

import java.time.LocalDateTime;
import java.util.Optional;

import com.ss.meet.meetapi.domain.schedule.Schedule;
import com.ss.meet.meetapi.domain.session.Session;
import com.ss.meet.meetapi.infra.util.MeetException;

import org.springframework.stereotype.Service;

@Service
public class SessionFactory {
    /**
     * Use to create a new session
     * 
     * @param schedule
     * @param duration
     * @return
     */
    public Session create(Schedule schedule, Integer duration) {
        final Session session = new Session(
                Optional.ofNullable(schedule).orElseThrow(() -> new MeetException("Schedule not found.")),
                LocalDateTime.now(), Optional.ofNullable(duration).orElse(1));
        return session.setEndAt(session.getStartAt().plusMinutes(session.getDuration()));
    }
}
