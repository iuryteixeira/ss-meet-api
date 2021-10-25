package com.ss.meet.meetapi.infra.messaging.consumers.impl;

import com.ss.meet.meetapi.infra.messaging.config.ConfigValues;
import com.ss.meet.meetapi.infra.messaging.config.VoteDataTransfer;
import com.ss.meet.meetapi.infra.messaging.consumers.VoteReceivedConsumer;
import com.ss.meet.meetapi.infra.util.MeetLogger;

import org.springframework.cloud.aws.messaging.config.annotation.NotificationMessage;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Controller;

@Controller
public class VoteReceivedSqsConsumer implements VoteReceivedConsumer{

    private final MeetLogger logger = MeetLogger.instance(VoteReceivedConsumer.class);

    @Override
    @SqsListener(value = ConfigValues.ORDER_QUEUE, deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void consume(@NotificationMessage VoteDataTransfer transfer) {
        logger.info("Received Vote -> {0}", transfer);
    }
    
}
