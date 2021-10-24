package com.ss.meet.meetapi.presentation.session.dto;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class SessionOpenDTO implements Serializable {

    @NotNull(message = "Schedule id is required")
    private Long scheduleId;
    @Min(value = 1, message = "Minimum duration is one[1] minute")
    private Integer duration;

    public Long getScheduleId() {
        return scheduleId;
    }
    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }
    public Integer getDuration() {
        return duration;
    }
    public void setDuration(Integer duration) {
        this.duration = duration;
    }
    @Override
    public String toString() {
        return "SessionOpenDTO [duration=" + duration + ", scheduleId=" + scheduleId + "]";
    }

}
