package com.shedhack.thread.context.handler;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * The most basic handler stores simple strings.
 * The client is responsible for managing the input and output strings.
 *
 * @author imamchishty
 */
public class SimpleThreadContextHandler implements ThreadContextHandler<String> {

    public SimpleThreadContextHandler() {
        this.afterSetHandlers = Collections.EMPTY_LIST;
    }

    public SimpleThreadContextHandler(List<ThreadContextAfterSet> afterSetHandlers) {
        this.afterSetHandlers = afterSetHandlers;
    }

    private final List<ThreadContextAfterSet> afterSetHandlers;

    /**
     * {@inheritDoc}
     */
    public void setThreadContext(String value) {

        if(value !=null && !value.isEmpty()) {
            Thread.currentThread().setName(value);

            // call the after setting handler
            this.afterSettingThreadContext(value, afterSetHandlers);
        }
    }

    /**
     * {@inheritDoc}
     */
    public Optional<String> getThreadContext() {
        return convertFromString(Thread.currentThread().getName());
    }

    @Override
    public Optional<String> convertFromString(String original) {
        return Optional.ofNullable(original);
    }
}
