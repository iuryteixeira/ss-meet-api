package com.ss.meet.meetapi.presentation.schedule.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

public class ScheduleCreateDTO implements Serializable {
    
    @NotBlank(message = "Subject cannot be empty")
    private String subject;

    public String getSubject() {
        return subject;
    }

    @Override
    public String toString() {
        return "ScheduleCreateDTO [subject=" + subject + "]";
    }

}
