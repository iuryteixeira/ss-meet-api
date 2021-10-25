package com.ss.meet.meetapi.endpoints;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ss.meet.meetapi.application.ScheduleSearchApplication;
import com.ss.meet.meetapi.domain.schedule.Schedule;
import com.ss.meet.meetapi.infra.util.MeetLogger;
import com.ss.meet.meetapi.presentation.schedule.ScheduleController;
import com.ss.meet.meetapi.presentation.schedule.dto.ScheduleCreateDTO;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ScheduleController.class)
public class ScheduleRestTest {

    private final MeetLogger logger = MeetLogger.instance(ScheduleRestTest.class);

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ScheduleSearchApplication searchApplication;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testSaveScheduleWithSubjectWithReturn201() throws Exception {
        final ScheduleCreateDTO schedule = new ScheduleCreateDTO().setSubject("ScheduleOne");
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/schedules")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(schedule));

        mvc.perform(requestBuilder)
            .andExpect(MockMvcResultMatchers.status().isCreated());
    }
    
    @Test
    public void testSaveScheduleWithoutSubjectWithReturn400() throws Exception {
        final ScheduleCreateDTO schedule = new ScheduleCreateDTO();
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/schedules")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(schedule));

        mvc.perform(requestBuilder)
            .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testListAllSchedule() throws Exception {
        final List<Schedule> allMock = Arrays.asList(new Schedule("ScheduleOne"), new Schedule("ScheduleTwo"));
        logger.info("Mocks {0}", allMock);
        Mockito.when(searchApplication.all()).thenReturn(allMock);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/schedules").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        logger.info("Response {0}", result.getResponse());
        String expected = "[{quantYesVotes:0,quantNoVotes:0,subject:ScheduleOne},{quantYesVotes:0,quantNoVotes:0,subject:ScheduleTwo}]";
        assertEquals(result.getResponse().getStatus(), HttpStatus.OK.value());
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

}
