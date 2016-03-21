package com.shedhack.thread.context.adapter;

import com.shedhack.thread.context.handler.JsonThreadContextHandler;
import com.shedhack.thread.context.handler.ThreadContextAfterSet;
import com.shedhack.thread.context.model.DefaultThreadContextModel;
import com.shedhack.thread.context.model.ThreadContextModel;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author imamchishty
 */
public class JsonThreadContextAdapter implements ThreadContextAdapter {

    private final JsonThreadContextHandler handler;

    public JsonThreadContextAdapter(JsonThreadContextHandler handler) {
        this.handler = handler;
    }

    /**
     * {@inheritDoc}
     */
    public void setContext(String id, Date date, String method, Map<String, Object> context, Map<String, Object> params) {
        ThreadContextModel model = new DefaultThreadContextModel();
        model.setId(id);
        model.setMethodName(method);
        model.setTimestamp(date);
        model.setParams(params);
        model.setContext(context);
        handler.setThreadContext(model);
    }
}
