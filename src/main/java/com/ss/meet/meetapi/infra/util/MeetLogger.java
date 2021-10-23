package com.ss.meet.meetapi.infra.util;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MeetLogger {
    private final Logger logger;

    private MeetLogger(Class context) {
        this.logger = LoggerFactory.getLogger(context);
    }

    public static MeetLogger instance(Class context){
        return new MeetLogger(context);
    }

    public void info(String text, Object... values){
        logger.info(MessageFormat.format(text, values));
    }
    
    public void error(String text, Object... values){
        logger.error(MessageFormat.format(text, values));
    }
    
    public void error(String text, Throwable throwable, Object... values){
        logger.error(MessageFormat.format(text, values), throwable);
    }
}
