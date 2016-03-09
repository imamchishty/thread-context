package com.shedhack.thread.context.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Detailed Context Model.
 * This is currently used by {@link com.shedhack.thread.context.handler.JsonThreadContextHandler}
 * 
 * @author imamchishty
 */
public class ThreadContextModel {

    // -----------------------------------
    // Static class for building the model
    // -----------------------------------

    public static class Builder {

        ThreadContextModel model;

        public Builder() {
            model = new ThreadContextModel();
        }

        public Builder withMethodName(String name) {
            model.methodName = name;
            return this;
        }

        public Builder withId(String id) {
            model.id = id;
            return this;
        }

        public Builder withParam(String key, Object value) {
            model.params.put(key, value);
            return this;
        }

        public Builder withContext(String key, Object value) {
            model.context.put(key, value);
            return this;
        }

        public Builder withDefaultDate() {
            model.timestamp = new Date();
            return this;
        }

        public Builder withDateTime(Date dateTime) {
            model.timestamp = dateTime;
            return this;
        }

        public ThreadContextModel build() {
            return model;
        }

    }

    // --------------
    // Static methods
    // --------------

    public static Builder builder() {
        return new Builder();
    }

    // ----------------------
    // Model class and fields
    // ----------------------

    private Map<String, Object> context = new HashMap<>();

    private String id;

    private String methodName;

    private Date timestamp;

    private Map<String, Object> params = new HashMap<>();

    public ThreadContextModel() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Map<String, Object> getContext() {
        return context;
    }

    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ThreadContextModel that = (ThreadContextModel) o;

        if (context != null ? !context.equals(that.context) : that.context != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (methodName != null ? !methodName.equals(that.methodName) : that.methodName != null) return false;
        if (params != null ? !params.equals(that.params) : that.params != null) return false;
        if (timestamp != null ? !timestamp.equals(that.timestamp) : that.timestamp != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = context != null ? context.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (methodName != null ? methodName.hashCode() : 0);
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        result = 31 * result + (params != null ? params.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DefaultThreadContextModel{" +
                "context=" + context +
                ", id='" + id + '\'' +
                ", methodName='" + methodName + '\'' +
                ", timestamp=" + timestamp +
                ", params=" + params +
                '}';
    }
}
