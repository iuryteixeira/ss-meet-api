package com.ss.meet.meetapi.application.impl;

import java.util.List;

import com.ss.meet.meetapi.application.ScheduleSearchApplication;
import com.ss.meet.meetapi.domain.schedule.Schedule;
import com.ss.meet.meetapi.infra.repository.ScheduleRepository;
import com.ss.meet.meetapi.infra.util.MeetException;
import com.ss.meet.meetapi.infra.util.MeetLogger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScheduleSearchApplicationImpl implements ScheduleSearchApplication {

    private MeetLogger logger = MeetLogger.instance(ScheduleSearchApplicationImpl.class);

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public Schedule findById(Long id) {
        logger.info("Before find by id {0}", id);
        return scheduleRepository.findById(id).orElseThrow(() -> new MeetException("Schedule not found", 404));
    }

    @Override
    public List<Schedule> all() {
        return scheduleRepository.findAll();
    }

}
