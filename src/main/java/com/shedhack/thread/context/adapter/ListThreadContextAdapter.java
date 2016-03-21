package com.shedhack.thread.context.adapter;

import com.shedhack.thread.context.handler.ListThreadContextHandler;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

/**
 * Created by imamchishty on 15/03/2016.
 */
public class ListThreadContextAdapter implements ThreadContextAdapter {

    public ListThreadContextAdapter(ListThreadContextHandler handler) {
        this.handler = handler;
    }

    private ListThreadContextHandler handler;

    /**
     * {@inheritDoc}
     */
    public void setContext(String id, Date date, String method, Map<String, Object> context, Map<String, Object> params) {
        handler.setThreadContext(Arrays.asList(id, date.toString(),
                method, context.toString(), params.toString()));

        System.out.println("STRING--->" + handler.getThreadContext());
    }
}
