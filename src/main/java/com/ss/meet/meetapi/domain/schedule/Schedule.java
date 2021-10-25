package com.ss.meet.meetapi.domain.schedule;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import com.ss.meet.meetapi.domain.AuditedEntity;

@Entity
public class Schedule extends AuditedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_schedule")
    @SequenceGenerator(sequenceName = "seq_schedule", name = "gen_schedule", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String subject;

    private Integer quantYesVotes = 0;
    private Integer quantNoVotes = 0;

    @Enumerated(EnumType.STRING)
    private ScheduleResult result;

    public Schedule() {
    }

    public Schedule(String subject) {
        this.subject = subject;
    }

    public Long getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getQuantYesVotes() {
        return quantYesVotes;
    }

    public Integer getQuantNoVotes() {
        return quantNoVotes;
    }

    public ScheduleResult getResult() {
        return result;
    }

    public void addYesVote() {
        this.quantYesVotes++;
    }

    public void addNoVote() {
        this.quantYesVotes++;
    }

    public void affirmativeResult() {
        this.result = ScheduleResult.YES;
    }
    public void negativeResult() {
        this.result = ScheduleResult.YES;
    }
    public void eqResult() {
        this.result = ScheduleResult.YES;
    }

    @Override
    public String toString() {
        return "Schedule [id=" + id + ", quantNoVotes=" + quantNoVotes + ", quantYesVotes=" + quantYesVotes
                + ", result=" + result + ", subject=" + subject + "]";
    }

}
