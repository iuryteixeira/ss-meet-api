package com.ss.meet.meetapi.presentation.vote;

import javax.validation.Valid;

import com.ss.meet.meetapi.application.VoteReceivedApplication;
import com.ss.meet.meetapi.domain.vote.Vote;
import com.ss.meet.meetapi.infra.util.MeetException;
import com.ss.meet.meetapi.infra.util.MeetLogger;
import com.ss.meet.meetapi.presentation.vote.dto.VoteCreateDTO;

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
@RequestMapping("/votes")
public class VoteController {

    private final MeetLogger logger = MeetLogger.instance(VoteController.class);
    private final VoteReceivedApplication receivedApplication;

    @Autowired
    public VoteController(final VoteReceivedApplication receivedApplication) {
        this.receivedApplication = receivedApplication;
    }

    /**
     * 
     * @return
     */
    @Operation(summary = "Receive session user vote")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Vote Received", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Vote.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid informations") })
    @PostMapping("")
    public ResponseEntity<?> open(@RequestBody @Valid VoteCreateDTO voteDTO) {
        try {
            logger.info("Received vote: {0}", voteDTO);
            receivedApplication.receive(voteDTO.getSessionId(), 
                        voteDTO.getValue(), voteDTO.getUserId());
            return new ResponseEntity<>(
                    HttpStatus.CREATED);
        } catch (MeetException e) {
            logger.error("fail receveid vote: {0}", e, voteDTO, e.getStatusCode());
            return new ResponseEntity<>(e.getMessages(), HttpStatus.valueOf(e.getStatusCode()));
        } catch (Exception e) {
            logger.error("fail receveid vote: {0}", e, voteDTO);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
