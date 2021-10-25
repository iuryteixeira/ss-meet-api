package com.ss.meet.meetapi.infra.messaging.config;

import org.springframework.beans.factory.annotation.Value;

public class ConfigValues {
    
    public static final String ACCESS_KEY = "access_aws";
    public static final String SECRET_KEY = "secret_aws";
    public static final String ENDPOINT = "http://localhost:4566";
    public static final String EU_CENTRAL_1 = "eu-central-1";

    @Value("${cloud.aws.sns.order-created.arn}")
    public static final String ORDER_CREATED_TOPIC = "order-created-topic";

    @Value("${cloud.aws.sqs.order-queue.url}")
    public static final String ORDER_QUEUE = "order-queue";

}