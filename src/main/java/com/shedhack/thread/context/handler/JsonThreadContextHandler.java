package com.shedhack.thread.context.handler;

import com.google.gson.Gson;
import com.shedhack.thread.context.model.ThreadContextModel;
import com.shedhack.thread.context.model.DefaultThreadContextModel;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Sets/Gets the thread context as a {@link com.shedhack.thread.context.model.DefaultThreadContextModel}.
 * {@link com.google.gson.Gson} is used to convert the object to a JSON string and back.
 *
 * @author imamchishty
 */
public class JsonThreadContextHandler implements ThreadContextHandler<ThreadContextModel> {

    private Gson GSON;

    private final List<ThreadContextAfterSet> afterHandlers;

    public JsonThreadContextHandler(Gson gson) {
        this.GSON = gson;
        this.afterHandlers = Collections.EMPTY_LIST;
    }

    public JsonThreadContextHandler(Gson gson, List<ThreadContextAfterSet> afterHandlers) {
        this.afterHandlers = afterHandlers;
        this.GSON = gson;
    }

    /**
     * {@inheritDoc}
     */
    public void setThreadContext(ThreadContextModel model) {

        if(model != null) {
            String converted = GSON.toJson(model);
            Thread.currentThread().setName(converted);

            // call the after setting handler
            this.afterSettingThreadContext(converted, afterHandlers);
        }
    }

    /**
     * Returns the {@link com.shedhack.thread.context.model.DefaultThreadContextModel}.
     * If for some reason the object cannot be hydrated using GSON then an null {@link java.util.Optional}
     * object is returned.
     */
    public Optional<ThreadContextModel> getThreadContext() {
        return convertFromString(Thread.currentThread().getName());
    }

    /**
     * {@inheritDoc}
     */
    public Optional<ThreadContextModel> convertFromString(String value) {
        try {
            return Optional.ofNullable(GSON.fromJson(value, DefaultThreadContextModel.class));
        }
        catch (Exception ex) {
            return Optional.empty();
        }
    }

}
