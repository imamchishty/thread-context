package com.shedhack.thread.context.adapter;

import java.util.Date;
import java.util.HashMap;

/**
 * Simple service shows how to use the {@link ThreadContextAdapter}
 *
 * @author imamchishty
 */
public class FooService {

    public FooService(ThreadContextAdapter adapter) {
        this.adapter = adapter;
    }

    final ThreadContextAdapter adapter;

    public int calc(int a, int b) {

        // Adapter could be called via a servlet, aspect. This example shows how
        // a servlet/adapter could use the adapter without knowing the underlying implementation of the
        // ThreadContextHandler. Values passed to the adapter have been hardcoded but in reality would be dynamically set.
        adapter.setContext("1234567890", new Date(), "com.shedhack.thread.context.adapter.FooService.calc",
                new HashMap<String, Object>(){{put("session-id", "ABC");}},
                    new HashMap<String, Object>(){{put("ARG0", a); put("ARG1", b);}});

        return a+b;
    }
}
