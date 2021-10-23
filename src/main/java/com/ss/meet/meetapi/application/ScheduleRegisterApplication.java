package com.ss.meet.meetapi.application;

import com.ss.meet.meetapi.domain.schedule.Schedule;

public interface ScheduleRegisterApplication {
    /**
     * Create a schedule by subject
     * 
     * @param subject
     * @return
     */
    Schedule register(final String subject);
}
