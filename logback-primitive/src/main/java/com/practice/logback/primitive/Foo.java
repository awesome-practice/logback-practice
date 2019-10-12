package com.practice.logback.primitive;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Foo {
    private static final Logger logger = LoggerFactory.getLogger(Foo.class);


    public void doIt() {
        execOfSize();
//        execOfTime();

    }

    private void execOfTime() {
        try {
            for (int i = 0; i < 10; i++) {
                logger.debug("Did it again!");
                logger.error("error again!");
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void execOfSize() {
        for (int i = 0; i < 1024 * 20; i++) {
            logger.debug("Did it again!");
            logger.error("Did it again!");
        }

    }


}