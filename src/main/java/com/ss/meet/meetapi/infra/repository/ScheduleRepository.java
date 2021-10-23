package com.ss.meet.meetapi.infra.repository;

import com.ss.meet.meetapi.domain.schedule.Schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.annotation.ApplicationScope;

@Repository
@ApplicationScope
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

}
