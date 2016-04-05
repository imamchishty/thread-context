package com.shedhack.thread.context.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 *     Logs the thread context, using slf4j, at INFO level.
 * </pre>
 *
 * @author imamchishty
 */
public class LoggingAfterSet implements ThreadContextAfterSet {

    private static Logger LOGGER = LoggerFactory.getLogger(LoggingAfterSet.class);

    /**
     * Logs the context at INFO level.
     */
    public void afterSet(String context) {
        LOGGER.info(context);
    }
}
