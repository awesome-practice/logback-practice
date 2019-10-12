package com.practice.logback.primitive;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

public class LoadLogbackConfigManually {
    private final static Logger logger = LoggerFactory.getLogger(LoadLogbackConfigManually.class);

    public static void main(String[] args) {
        config3();

//        config2();

        logger.info("Entering application.");

        Foo foo = new Foo();
        foo.doIt();
        logger.info("Exiting application.");
    }

    private static void config3() {//recommend, let the xml file placed in the same package
        URL resource = LoadLogbackConfigManually.class.getResource("logback5.xml");

        configLogback(resource);
    }

    private static void config2() {
        URL resource = LoadLogbackConfigManually.class.getClassLoader().getResource("logback3.xml");

        configLogback(resource);
    }


    private static void configLogback(URL url) {
        // assume SLF4J is bound to logback in the current environment
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();

        try {
            JoranConfigurator configurator = new JoranConfigurator();
            configurator.setContext(context);
            // Call context.reset() to clear any previous configuration, e.g. default
            // configuration. For multi-step configuration, omit calling context.reset().
//            context.reset();
            configurator.doConfigure(url);
        } catch (JoranException je) {
            // StatusPrinter will handle this
        }
        StatusPrinter.printInCaseOfErrorsOrWarnings(context);
    }


}