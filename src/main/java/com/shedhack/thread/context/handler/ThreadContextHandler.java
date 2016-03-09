package com.shedhack.thread.context.handler;

import java.util.Optional;

public interface ThreadContextHandler<T> {

    /**
     * Sets the thread context based on the generic type.
     */
    void setThreadContext(T context);

    /**
     * Optional returns back the thread context.
     */
    Optional<T> getThreadContext();
}
