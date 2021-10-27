package com.ss.meet.meetapi.infra.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.ss.meet.meetapi.domain.session.Session;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    Optional<Session> findByScheduleId(Long scheduleId);

    @Query("select se from Session se inner join se.schedule s where s.id = :scheduleId and se.endAt <= :currentTime")
    List<Session> findAllByEndAtLessThanEqualAndScheduleId(
        @Param("currentTime") LocalDateTime currentTime,
        @Param("scheduleId") Long scheduleId);
}
