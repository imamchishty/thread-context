package com.shedhack.thread.context.handler;

import com.shedhack.thread.context.model.ThreadContextModel;

import java.util.Optional;

public interface ThreadContextHandler {

    /**
     * Sets the thread context based on the generic type.
     */
    void setThreadContext(ThreadContextModel context);

    /**
     * Optional returns back the thread context.
     */
    Optional<ThreadContextModel> getThreadContext();
}
