package com.ss.meet.meetapi.infra.messaging.consumers;

import com.ss.meet.meetapi.infra.messaging.config.VoteDataTransfer;

import org.springframework.cloud.aws.messaging.config.annotation.NotificationMessage;

public interface VoteReceivedConsumer {

    public void consume(@NotificationMessage VoteDataTransfer transfer);

}
