package com.ss.meet.meetapi.presentation.session;

import javax.validation.Valid;

import com.ss.meet.meetapi.application.SessionOpenApplication;
import com.ss.meet.meetapi.domain.session.Session;
import com.ss.meet.meetapi.infra.util.MeetException;
import com.ss.meet.meetapi.infra.util.MeetLogger;
import com.ss.meet.meetapi.presentation.session.dto.SessionOpenDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/sessions")
public class SessionController {

    private final MeetLogger logger = MeetLogger.instance(SessionController.class);
    private final SessionOpenApplication sessionOpenApplication;

    @Autowired
    public SessionController(SessionOpenApplication sessionOpenApplication) {
        this.sessionOpenApplication = sessionOpenApplication;
    }

    /**
     * 
     * @return
     */
    @Operation(summary = "Open a session for one Schedule")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Session Opened", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Session.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid informations") })
    @PostMapping("")
    public ResponseEntity<?> open(@RequestBody @Valid SessionOpenDTO openDTO) {
        try {
            logger.info("Received session to open: {0}", openDTO);
            return new ResponseEntity<>(sessionOpenApplication.open(openDTO.getScheduleId(), openDTO.getDuration()),
                    HttpStatus.CREATED);
        } catch (MeetException e) {
            logger.error("fail to open session: {0} code {1}", e, openDTO, e.getStatusCode());
            return new ResponseEntity<>(e.getMessages(), HttpStatus.valueOf(e.getStatusCode()));
        } catch (Exception e) {
            logger.error("fail to open session: {0}", e, openDTO);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
