package com.shedhack.thread.context.adapter;

import com.shedhack.thread.context.handler.SimpleThreadContextHandler;

import java.util.Date;
import java.util.Map;

/**
 * Created by imamchishty on 15/03/2016.
 */
public class SimpleThreadContextAdapter implements ThreadContextAdapter {

    public SimpleThreadContextAdapter(SimpleThreadContextHandler handler) {
        this.handler = handler;
    }

    private SimpleThreadContextHandler handler;

    private static final String SEP = "~";

    /**
     * {@inheritDoc}
     */
    public void setContext(String id, Date date, String method, Map<String, Object> context, Map<String, Object> params) {
        handler.setThreadContext(id + SEP + date
                + SEP + method
                + SEP + context + SEP
                + params);
    }
}
