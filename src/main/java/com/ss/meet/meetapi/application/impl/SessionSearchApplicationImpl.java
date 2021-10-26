package com.ss.meet.meetapi.application.impl;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.ss.meet.meetapi.application.SessionSearchApplication;
import com.ss.meet.meetapi.domain.schedule.Schedule;
import com.ss.meet.meetapi.domain.session.Session;
import com.ss.meet.meetapi.infra.repository.SessionRepository;
import com.ss.meet.meetapi.infra.util.MeetException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionSearchApplicationImpl implements SessionSearchApplication {

    private final SessionRepository sessionRepository;

    @Autowired
    public SessionSearchApplicationImpl(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Override
    public Session findById(Long id) {
        return sessionRepository.findById(id).orElseThrow(() -> new MeetException("Session not found.", 404));
    }

    @Override
    public List<Session> all() {
        return sessionRepository.findAll();
    }

    @Override
    public Session findByScheduleId(Long id) {
        return sessionRepository.findByScheduleId(id).orElseThrow(() -> new MeetException("Session not found.", 404));
    }

    @Override
    public List<Session> findFinishedSessionsBySchedule(Schedule schedule) {
        return sessionRepository.findAllByEndAtLessThanEqualAndScheduleId(LocalDateTime.now(), schedule.getId());
    }

}
