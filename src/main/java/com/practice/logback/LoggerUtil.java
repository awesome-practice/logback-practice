package com.practice.logback;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy;
import ch.qos.logback.core.util.FileSize;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * @author Luo Bao Ding
 * @since 2018/10/20
 */
public class LoggerUtil {
    public static final String LOGGING_LINE_PATTERN = "%d{yyyy-MM-dd_HH:mm:ss.SSS} %msg%n";

    public static final String LOGS_ROOT_DIR = "logs";
    public static final String LOGS_HISTORY_DIR = LOGS_ROOT_DIR + File.separator + "history";
    public static final String APPENDER_PREFIX = "APPENDER-";
    public static final String LOG_FILE_SUFFIX = ".log";
    public static final String FILE_NAME_ROLLING_PATTERN = ".%d";
    public static final int MAX_HISTORY = 14;
    public static final String TOTAL_SIZE_CAP = "3GB";

    public static Logger getLogger(String logName) {
        String filePath = LOGS_ROOT_DIR + File.separator + logName.toLowerCase() + LOG_FILE_SUFFIX;
        String fileName = logName.toLowerCase();
        logName = logName.toUpperCase();

        LoggerContext logCtx = (LoggerContext) LoggerFactory.getILoggerFactory();

        PatternLayoutEncoder logEncoder = new PatternLayoutEncoder();
        logEncoder.setPattern(LOGGING_LINE_PATTERN);
        logEncoder.setContext(logCtx);
        logEncoder.start();

        RollingFileAppender<ILoggingEvent> logFileAppender = new RollingFileAppender<>();
        logFileAppender.setContext(logCtx);
        logFileAppender.setName(APPENDER_PREFIX + logName);
        logFileAppender.setEncoder(logEncoder);
        logFileAppender.setAppend(true);
        logFileAppender.setFile(filePath);

        TimeBasedRollingPolicy logFilePolicy = new TimeBasedRollingPolicy();
        logFilePolicy.setContext(logCtx);
        logFilePolicy.setParent(logFileAppender);

        String rollingFilePathPattern = LOGS_HISTORY_DIR + File.separator
                + fileName + FILE_NAME_ROLLING_PATTERN + LOG_FILE_SUFFIX;
        logFilePolicy.setFileNamePattern(rollingFilePathPattern);

        logFilePolicy.setMaxHistory(MAX_HISTORY);
        logFilePolicy.setTotalSizeCap(FileSize.valueOf(TOTAL_SIZE_CAP));
        logFilePolicy.start();

        logFileAppender.setRollingPolicy(logFilePolicy);
        logFileAppender.start();

        ch.qos.logback.classic.Logger logger = logCtx.getLogger(logName);
        logger.addAppender(logFileAppender);
        logger.setLevel(Level.DEBUG);
        logger.setAdditive(false); /* set to true if root should log too */

        return logger;
    }
}
