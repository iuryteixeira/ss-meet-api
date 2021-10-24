package com.ss.meet.meetapi.domain.session;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import com.ss.meet.meetapi.domain.AuditedEntity;
import com.ss.meet.meetapi.domain.schedule.Schedule;

@Entity
public class Session extends AuditedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_session")
    @SequenceGenerator(sequenceName = "seq_session", name = "gen_session", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime startAt;

    @Column(nullable = false)
    private LocalDateTime endAt;

    @Column(nullable = false)
    private Integer duration;

    @OneToOne
    @JoinColumn(nullable = false)
    private Schedule schedule;

    public Session() {
    }

    /**
     * Create a session with start time and duration time
     * 
     * @param startAt
     * @param duration
     */
    public Session(Schedule schedule, LocalDateTime startAt, Integer duration) {
        this.startAt = startAt;
        this.duration = duration;
        this.schedule = schedule;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getStartAt() {
        return startAt;
    }

    public LocalDateTime getEndAt() {
        return endAt;
    }

    public Session setEndAt(LocalDateTime endAt) {
        this.endAt = endAt;
        return this;
    }

    public Integer getDuration() {
        return duration;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    @Override
    public String toString() {
        return "Session [duration=" + duration + ", endAt=" + endAt + ", id=" + id + ", schedule=" + schedule
                + ", startAt=" + startAt + "]";
    }

}
