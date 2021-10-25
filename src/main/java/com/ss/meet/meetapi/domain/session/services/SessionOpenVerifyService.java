package com.ss.meet.meetapi.domain.session.services;

import java.time.LocalDateTime;

import com.ss.meet.meetapi.domain.session.Session;
import com.ss.meet.meetapi.infra.util.MeetException;

import org.springframework.stereotype.Service;

@Service
public class SessionOpenVerifyService {

    public void verify(Session session) {
        if (LocalDateTime.now().isAfter(session.getEndAt()))
            throw new MeetException("Finished session");
    }

}
