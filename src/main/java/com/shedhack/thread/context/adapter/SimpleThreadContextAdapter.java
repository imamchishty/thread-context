package com.shedhack.thread.context.adapter;

import com.shedhack.thread.context.handler.SimpleThreadContextHandler;

import java.util.Date;
import java.util.Map;

/**
 * @author imamchishty
 */
public class SimpleThreadContextAdapter implements ThreadContextAdapter {

    public SimpleThreadContextAdapter(SimpleThreadContextHandler handler) {
        this.handler = handler;
    }

    private SimpleThreadContextHandler handler;

    private static final String TEXT = "{\"spanId\": \"%s\", \"timestamp\": \"%s\", " +
            "\"method\": \"%s\", \"params\": \"%s\", \"context\": \"%s\"}";

    /**
     * {@inheritDoc}
     */
    public void setContext(String id, Date date, String method, Map<String, Object> context, Map<String, Object> params) {
        handler.setThreadContext(String.format(TEXT, id, date, method, params, context));

    }
}
