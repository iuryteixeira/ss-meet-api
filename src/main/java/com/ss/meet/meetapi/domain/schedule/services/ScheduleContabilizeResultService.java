package com.ss.meet.meetapi.domain.schedule.services;

import com.ss.meet.meetapi.domain.schedule.Schedule;

import org.springframework.stereotype.Service;

@Service
public class ScheduleContabilizeResultService {

    public void execute(final Schedule schedule) {
        if (schedule.getQuantYesVotes() > schedule.getQuantNoVotes()) {
            schedule.affirmativeResult();
        } else if (schedule.getQuantYesVotes() < schedule.getQuantNoVotes()) {
            schedule.negativeResult();
        } else {
            schedule.eqResult();
        }
    }

}
