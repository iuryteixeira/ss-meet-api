package com.ss.meet.meetapi.domain.schedule.services;

import java.util.List;

import com.ss.meet.meetapi.domain.schedule.Schedule;
import com.ss.meet.meetapi.domain.vote.Vote;

import org.springframework.stereotype.Service;

@Service
public class ScheduleSumResultService {

    public void execute(Schedule schedule, final List<Vote> votes) {
        votes.forEach(v -> {
            switch (v.getValue()) {
            case YES:
                schedule.addYesVote();
                break;
            case NO:
                schedule.addNoVote();
                break;
            }
        });
    }

}
