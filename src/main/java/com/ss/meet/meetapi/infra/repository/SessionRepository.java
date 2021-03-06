package com.ss.meet.meetapi.infra.repository;

import java.util.Optional;

import com.ss.meet.meetapi.domain.session.Session;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    Optional<Session> findByScheduleId(Long scheduleId);
}
