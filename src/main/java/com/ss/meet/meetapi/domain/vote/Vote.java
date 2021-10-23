package com.ss.meet.meetapi.domain.vote;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.ss.meet.meetapi.domain.AuditedEntity;
import com.ss.meet.meetapi.domain.session.Session;

@Entity
public class Vote extends AuditedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_vote")
    @SequenceGenerator(sequenceName = "seq_vote", name = "gen_vote", initialValue = 1, allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, updatable = false)
    private Session session;

    @Column(nullable = false, updatable = false)
    private String userId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, updatable = false)
    private VoteValue value;

    public Vote() {
    }
    
    public Vote(Session session, String userId, VoteValue value) {
        this.session = session;
        this.userId = userId;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public Session getSession() {
        return session;
    }

    public String getUserId() {
        return userId;
    }

    public VoteValue getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Vote [id=" + id + ", session=" + session + ", userId=" + userId + ", value=" + value + "]";
    }

}
