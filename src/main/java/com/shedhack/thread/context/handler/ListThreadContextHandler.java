package com.shedhack.thread.context.handler;

import java.util.*;
import java.util.stream.Collectors;

/**
 * A simple list of Strings can be stored as the thread context.
 * When the list is converted to a string each element is separated using ~.
 *
 * @author imamchishty
 */
public class ListThreadContextHandler implements ThreadContextHandler<List<String>> {

    private static final String SEP = "~";

    /**
     * Stores the list (comma separated) as the thread name.
     */
    public void setThreadContext(List<String> list) {

        if(list != null && !list.isEmpty()) {
            Thread.currentThread().setName(list.stream()
                    .collect(Collectors.joining(SEP)));
        }
    }

    /**
     * Returns the list as an {@link java.util.Optional} type.
     */
    public Optional<List<String>> getThreadContext() {
        return convertFromString(Thread.currentThread().getName());
    }

    /**
     * {@inheritDoc}
     */
    public Optional<List<String>> convertFromString(String original) {
        return Optional.ofNullable(Arrays.asList(original.split(SEP)));
    }
}
