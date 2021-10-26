package com.ss.meet.meetapi.application;

import java.util.List;

import com.ss.meet.meetapi.domain.schedule.Schedule;

public interface ScheduleSearchApplication extends AbstractSearchApplication<Schedule, Long> {

    List<Schedule> findByNoResult();

}