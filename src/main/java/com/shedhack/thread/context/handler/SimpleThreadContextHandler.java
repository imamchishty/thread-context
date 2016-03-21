package com.shedhack.thread.context.handler;

import java.util.Optional;

/**
 * The most basic handler stores simple strings.
 * The client is responsible for managing the input and output strings.
 *
 * @author imamchishty
 */
public class SimpleThreadContextHandler implements ThreadContextHandler<String> {

    /**
     * {@inheritDoc}
     */
    public void setThreadContext(String value) {

        if(value !=null && !value.isEmpty()) {
            Thread.currentThread().setName(value);
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
