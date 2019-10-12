package com.practice.logback.primitive;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogbackTestXml {
    final static Logger logger = LoggerFactory.getLogger(LogbackTestXml.class);

    public static void main(String[] args) {
//        // assume SLF4J is bound to logback in the current environment
//        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
//        // print logback's internal status
//        StatusPrinter.print(lc);

        logger.info("Entering application.");
        Foo foo = new Foo();
        foo.doIt();
        logger.info("Exiting application.");
    }
}