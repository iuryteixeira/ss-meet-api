package com.ss.meet.meetapi.presentation.schedule;

import java.util.List;

import javax.validation.Valid;

import com.ss.meet.meetapi.application.ScheduleRegisterApplication;
import com.ss.meet.meetapi.domain.schedule.Schedule;
import com.ss.meet.meetapi.infra.util.MeetException;
import com.ss.meet.meetapi.infra.util.MeetLogger;
import com.ss.meet.meetapi.presentation.error.ErrorDTO;
import com.ss.meet.meetapi.presentation.schedule.dto.ScheduleCreateDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

	private MeetLogger logger = MeetLogger.instance(ScheduleController.class);

	@Autowired
	private ScheduleRegisterApplication scheduleRegisterApplication;

	/**
	 * 
	 * @param scheduleCreateDTO
	 * @return
	 */
	@Operation(summary = "Create a schedule")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Schedule Created", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Schedule.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid fields", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDTO.class))) })
	@PostMapping("/")
	public ResponseEntity<?> create(@RequestBody @Valid ScheduleCreateDTO scheduleCreateDTO) {
		try {
			logger.info("Received schedule {0}", scheduleCreateDTO);
			scheduleRegisterApplication.register(scheduleCreateDTO.getSubject());
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (MeetException e) {
			return new ResponseEntity<>(e.getMessages(), HttpStatus.resolve(e.getStatusCode()));
		}
	}

	@Operation(summary = "List all schedule")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "List all tools", content = @Content(mediaType = "application/json", 
				array = @ArraySchema(schema = @Schema(implementation = Schedule.class)))) })
	@GetMapping(value = "/")
	public ResponseEntity<List<Schedule>> list() {
		try {
			
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (MeetException e) {
			return null;//new ResponseEntity<>(e.getMessages(), HttpStatus.resolve(e.getStatusCode()));
		}
	}

}
