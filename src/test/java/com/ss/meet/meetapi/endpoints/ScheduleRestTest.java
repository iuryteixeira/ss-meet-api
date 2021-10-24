package com.ss.meet.meetapi.endpoints;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import com.ss.meet.meetapi.application.ScheduleRegisterApplication;
import com.ss.meet.meetapi.application.ScheduleSearchApplication;
import com.ss.meet.meetapi.domain.schedule.Schedule;
import com.ss.meet.meetapi.infra.util.MeetLogger;
import com.ss.meet.meetapi.presentation.schedule.ScheduleController;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ScheduleController.class)
public class ScheduleRestTest {

    private final MeetLogger logger = MeetLogger.instance(ScheduleRestTest.class);

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ScheduleRegisterApplication registerApplication;

    @MockBean
    private ScheduleSearchApplication searchApplication;

    public void testSaveScheduleWithSubjectResultSuccess201() throws Exception {
        final Schedule schedule = new Schedule("ScheduleOne");
        Mockito.when(registerApplication.register("ScheduleOne")).thenReturn(schedule);
        
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/schedules").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        assertEquals(HttpStatus.CREATED.value(), result.getResponse().getStatus());
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
