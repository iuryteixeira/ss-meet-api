package com.ss.meet.meetapi.application;

import com.ss.meet.meetapi.domain.session.Session;

public interface SessionOpenApplication {
    Session open(Long scheduleId);
    Session open(Long scheduleId, Integer duration);
}
