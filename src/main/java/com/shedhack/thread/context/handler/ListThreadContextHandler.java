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

    private final List<ThreadContextAfterSet> afterSetHandlers;

    public ListThreadContextHandler() {
        this.afterSetHandlers = Collections.EMPTY_LIST;
    }

    public ListThreadContextHandler(List<ThreadContextAfterSet> afterSetHandlers) {
        this.afterSetHandlers = afterSetHandlers;
    }

    /**
     * Stores the list (comma separated) as the thread name.
     */
    public void setThreadContext(List<String> list) {

        if(list != null && !list.isEmpty()) {

            String converted = list.stream()
                    .collect(Collectors.joining(SEP));

            Thread.currentThread().setName(converted);

            // call the after setting handler
            this.afterSettingThreadContext(converted, afterSetHandlers);
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
