package com.ss.meet.meetapi.domain.schedule.services;

import java.util.List;

import com.ss.meet.meetapi.domain.schedule.Schedule;
import com.ss.meet.meetapi.domain.vote.Vote;
import com.ss.meet.meetapi.domain.vote.VoteValue;

import org.springframework.stereotype.Service;

@Service
public class ScheduleSumResultService {

    private final ScheduleContabilizeResultService contabilizeResultService;

    public ScheduleSumResultService(ScheduleContabilizeResultService contabilizeResultService) {
        this.contabilizeResultService = contabilizeResultService;
    }

    public void execute(final Schedule schedule, final List<Vote> votes) {
        votes.forEach(v -> {
            if (v.getValue().equals(VoteValue.YES)) {
                schedule.addYesVote();
            } else {
                schedule.addNoVote();
            }
        });
        contabilizeResultService.execute(schedule);
    }

}
