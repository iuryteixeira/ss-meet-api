package com.ss.meet.meetapi.application;

import java.util.List;

import com.ss.meet.meetapi.domain.schedule.Schedule;
import com.ss.meet.meetapi.domain.session.Session;

public interface SessionSearchApplication extends AbstractSearchApplication<Session, Long>{

    Session findByScheduleId(Long id);

    List<Session> findFinishedSessionsBySchedule(Schedule schedule);
    
}
