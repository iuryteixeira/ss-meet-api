package com.ss.meet.meetapi.application;

import com.ss.meet.meetapi.domain.session.Session;

public interface SessionSearchApplication extends AbstractSearchApplication<Session, Long>{

    Session findByScheduleId(Long id);
    
}
