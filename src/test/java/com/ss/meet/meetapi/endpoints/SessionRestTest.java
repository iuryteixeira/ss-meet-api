package com.ss.meet.meetapi.endpoints;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ss.meet.meetapi.application.SessionOpenApplication;
import com.ss.meet.meetapi.presentation.session.SessionController;
import com.ss.meet.meetapi.presentation.session.dto.SessionOpenDTO;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(value = SessionController.class)
public class SessionRestTest {
 
    @Autowired
    private MockMvc mvc;

    @MockBean
    private SessionOpenApplication openApplication;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testOpenSessionWithDurationReturn201() throws Exception {
        final SessionOpenDTO session = new SessionOpenDTO()
            .setScheduleId(2L)
            .setDuration(7);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/sessions")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(session));

        mvc.perform(requestBuilder)
            .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testOpenSessionWithoutDurationReturn201() throws Exception {
        final SessionOpenDTO session = new SessionOpenDTO()
            .setScheduleId(2L);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/sessions")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(session));

        mvc.perform(requestBuilder)
            .andExpect(MockMvcResultMatchers.status().isCreated());
    }
    
    @Test
    public void testOpenSessionWithoutBodyReturn400() throws Exception {
        final SessionOpenDTO session = new SessionOpenDTO();
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/sessions")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(session));

        mvc.perform(requestBuilder)
            .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

}
