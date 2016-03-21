package com.shedhack.thread.context.handler;

/**
 * Allows the ability to do 'something' after the context has been set.
 * This could include stuff like logging the context to a db/file.
 */
public interface ThreadContextAfterSet {

    void afterSet(String context);

}
