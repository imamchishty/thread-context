package com.shedhack.thread.context.handler;

import java.util.List;
import java.util.Optional;

/**
 * <pre>
 *     API for handling thread contexts. This will store the type T to the current thread.
 *     Several implementations have been provided:
 *
 *      {@link com.shedhack.thread.context.handler.JsonThreadContextHandler}
 *      {@link com.shedhack.thread.context.handler.ListThreadContextHandler}
 *      {@link com.shedhack.thread.context.handler.SimpleThreadContextHandler}
 *
 *      Each implementations could use a matching {@link com.shedhack.thread.context.adapter.ThreadContextAdapter}
 *      if you wish to provide a consistent API.
 * </pre>
 *
 * @param <T> Type of object that will be stored in the context and also to be returned if required.
 *
 * @author imamchishty
 */
public interface ThreadContextHandler<T> {

    /**
     * Sets the thread context based on the generic type.
     */
    void setThreadContext(T context);

    /**
     * Optional returns back the thread context.
     */
    Optional<T> getThreadContext();

    /**
     * Converts from a String to the type T.
     */
     Optional<T> convertFromString(String original) ;

    /**
     * Returns the raw value without any type conversions.
     */
    default String getRawContext() {
        return Thread.currentThread().getName();
    }

    /**
     * Method is called after the thread context has been set. Allows you to do some extra stuff with
     * the context, e.g. logging
     */
    default void afterSettingThreadContext(String context, List<ThreadContextAfterSet> afterSetList) {
        for(ThreadContextAfterSet after : afterSetList) {
            after.afterSet(context);
        }
    }
}
