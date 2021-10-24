package com.ss.meet.meetapi.endpoints;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ss.meet.meetapi.application.SessionOpenApplication;
import com.ss.meet.meetapi.domain.schedule.Schedule;
import com.ss.meet.meetapi.presentation.session.SessionController;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(value = SessionController.class)
public class SessionRestTest {
 
    @Autowired
    private MockMvc mock;

    @MockBean
    private SessionOpenApplication openApplication;

    @Autowired
    private ObjectMapper objectMapper;
    
    @Test
    public void withDurationIspresent() {
        Schedule schedule = new Schedule("Assunto da Pauta");
        //List<Employee> allEmployees = Arrays.asList(alex);


    }

}
