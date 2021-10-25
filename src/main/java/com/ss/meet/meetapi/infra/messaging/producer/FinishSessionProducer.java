package com.ss.meet.meetapi.infra.messaging.producer;

import com.ss.meet.meetapi.infra.messaging.config.ConfigValues;
import com.ss.meet.meetapi.infra.util.MeetLogger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class FinishSessionProducer {

    private final MeetLogger logger = MeetLogger.instance(FinishSessionProducer.class);

    @Autowired
    private NotificationMessagingTemplate notificationMessagingTemplate;

    public void publish(FinishVoteDataTransfer data) {
        logger.info("Votes will be publisher {0}", data);
        notificationMessagingTemplate.convertAndSend(ConfigValues.ORDER_CREATED_TOPIC, data);
        logger.info("Votes published {0}", data);
    }

}
