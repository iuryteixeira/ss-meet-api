package com.ss.meet.meetapi.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.ss.meet.meetapi.domain.schedule.Schedule;
import com.ss.meet.meetapi.domain.schedule.services.ScheduleFactory;
import com.ss.meet.meetapi.domain.session.Session;
import com.ss.meet.meetapi.domain.session.services.SessionFactory;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest() 
public class SessionServicesTest {

    @Autowired
    private ScheduleFactory scheduleFactory;

    @Autowired
    private SessionFactory sessionFactory;

    @Test
    public void testWhenDurationIsNotPresentIsOne() {
        final Schedule schedule = scheduleFactory.create("Descrição da Pauta"); 
        final Session session = sessionFactory.create(schedule, null);
        assertEquals(session.getDuration(), 1);
    }

    @Test
    public void testWhenDurationIsPresent() {
        final int duration = 10;
        final Schedule schedule = scheduleFactory.create("Descrição da Pauta"); 
        final Session session = sessionFactory.create(schedule, duration);
        assertEquals(session.getDuration(), duration);
    }

}
