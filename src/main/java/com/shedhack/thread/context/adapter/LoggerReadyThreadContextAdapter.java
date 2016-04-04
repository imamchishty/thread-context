package com.shedhack.thread.context.adapter;

import com.shedhack.thread.context.handler.SimpleThreadContextHandler;

import java.util.Date;
import java.util.Map;

/**
 * This implementation assumes that the user log the context and will use preset MDC attributes,
 * this implies that we no longer require the id (as this should be set globally in the MDC) and
 * the date/time.
 *
 * So the thread context only contains, method, params and context.
 *
 * @author imamchishty
 */
public class LoggerReadyThreadContextAdapter implements ThreadContextAdapter {

    public LoggerReadyThreadContextAdapter(SimpleThreadContextHandler handler) {
        this.handler = handler;
    }

    private SimpleThreadContextHandler handler;

    private static final String SEP = ",";

    /**
     * {@inheritDoc}
     */
    public void setContext(String id, Date date, String method, Map<String, Object> context, Map<String, Object> params) {
        handler.setThreadContext(method + SEP + params + SEP + context);
    }
}
