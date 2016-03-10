package com.shedhack.thread.context.model;

import java.util.Date;
import java.util.Map;

/**
 * Context Model
 *
 * @author imamchishty
 */
public interface ThreadContextModel {

    void setMethodName(String name);

    String getMethodName();

    void setId(String id);

    String getId();

    void setParams(Map<String, Object> params);

    void addParam(String key, Object value);

    Map<String, Object> getParams();

    void setContext(Map<String, Object> context);

    void addContext(String key, Object context);

    Map<String, Object> getContext();

    void setTimestamp(Date dateTime);

    Date getTimestamp();
}
