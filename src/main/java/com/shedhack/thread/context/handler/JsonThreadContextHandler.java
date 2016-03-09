package com.shedhack.thread.context.handler;

import com.google.gson.Gson;
import com.shedhack.thread.context.model.ThreadContextModel;

import java.util.Optional;

/**
 * Sets/Gets the thread context as a {@link ThreadContextModel}.
 * {@link com.google.gson.Gson} is used to convert the object to a JSON string and back.
 *
 * @author imamchishty
 */
public class JsonThreadContextHandler implements ThreadContextHandler<ThreadContextModel> {

    private static Gson GSON = new Gson();

    /**
     * {@inheritDoc}
     */
    public void setThreadContext(ThreadContextModel model) {

        if(model != null) {
            Thread.currentThread().setName(GSON.toJson(model).toString());
        }
    }

    /**
     * Returns the {@link com.shedhack.thread.context.model.ThreadContextModel}.
     * If for some reason the object cannot be hydrated using GSON then an null {@link java.util.Optional}
     * object is returned.
     */
    public Optional<ThreadContextModel> getThreadContext() {
        try {
            return Optional.ofNullable(GSON.fromJson(Thread.currentThread().getName(), ThreadContextModel.class));
        }
        catch (Exception ex) {
            return Optional.ofNullable(null);
        }
    }
}
