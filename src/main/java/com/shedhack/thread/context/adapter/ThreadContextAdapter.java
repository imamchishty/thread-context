package com.shedhack.thread.context.adapter;

import java.util.Date;
import java.util.Map;

/**
 * <pre>
 *     ThreadContextAdapter provides a consistent API wrapper for dealing with {@link com.shedhack.thread.context.handler.ThreadContextHandler}.
 *     As {@link com.shedhack.thread.context.handler.ThreadContextHandler} uses generics a new ThreadContextAdapter implementation will required to 'wrap' it up.
 *     Several implementations have been provided, one for each of the ThreadContextHandlers.
 * </pre>
 * @author imamchishty
 */
public interface ThreadContextAdapter {

    /**
     * The adapter uses the params to create the context for the thread.
     *
     * @param id Unique Id. This is best suited for the Request Id rather than the Session Id. The session Id could be set in the context.
     * @param date Date/time for the request.
     * @param method Ideally this returns the fully qualified method name.
     * @param context Context may contain items such as the htto method, the http request path, session Id etc.
     * @param params Method params, the name of the params will be set to ARGx, e.g. ARG0, ARG1
     */
    void setContext(String id, Date date, String method, Map<String, Object> context, Map<String, Object> params);

}
