package com.ss.meet.meetapi.infra.messaging.config;


import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;

import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class SnsConfig {
    
    @Bean
    public NotificationMessagingTemplate notificationMessagingTemplate(AmazonSNS amazonSNS) {
        
        return new NotificationMessagingTemplate(amazonSNS);
    }

    @Bean
    @Primary
    public AmazonSNS amazonSNS(final AwsClientBuilder.EndpointConfiguration endpointConfiguration) {
        BasicAWSCredentials credentials = new BasicAWSCredentials(ConfigValues.ACCESS_KEY, ConfigValues.SECRET_KEY);
        return AmazonSNSClientBuilder
                .standard()
                .withEndpointConfiguration(endpointConfiguration)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
    }
}
