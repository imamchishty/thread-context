package com.shedhack.thread.context.adapter;

import com.shedhack.thread.context.handler.JsonThreadContextHandler;
import com.shedhack.thread.context.handler.ListThreadContextHandler;
import com.shedhack.thread.context.handler.SimpleThreadContextHandler;
import com.shedhack.thread.context.model.ThreadContextModel;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Test class to verify all implementations of {@link com.shedhack.thread.context.adapter.ThreadContextAdapter}
 */
public class ThreadContextHandlerTest {

    // ---------------------------------------------------
    // ThreadContextHandlers - one for each implementation
    // ---------------------------------------------------
    private JsonThreadContextHandler jsonHandler = new JsonThreadContextHandler();
    private ListThreadContextHandler listHandler = new ListThreadContextHandler();
    private SimpleThreadContextHandler simpleHandler = new SimpleThreadContextHandler();

    // ---------------------------------------------------
    // ThreadContextAdapters - one for each implementation
    // ---------------------------------------------------
    private ThreadContextAdapter jsonAdapter = new JsonThreadContextAdapter(jsonHandler);
    private ThreadContextAdapter listAdapter = new ListThreadContextAdapter(listHandler);
    private ThreadContextAdapter simpleAdapter = new SimpleThreadContextAdapter(simpleHandler);

    // ----------------------------------------------
    // Foo Service, three instances - purely for test
    // ----------------------------------------------
    private FooService fooJson = new FooService(jsonAdapter);
    private FooService fooList = new FooService(listAdapter);
    private FooService fooSimple = new FooService(simpleAdapter);

    @Test
    public void should_create_json_thread_context() {

        // Arrange

        // Act
        fooJson.calc(99, 100);

        // Get the context - tests running on the same thread
        Optional<ThreadContextModel> model = jsonHandler.getThreadContext();

        // Assert
        assertTrue(model.isPresent());

        // check all of the model props
        assertEquals("com.shedhack.thread.context.adapter.FooService.calc", model.get().getMethodName());

        assertNotNull(model.get().getId());
        assertEquals("1234567890", model.get().getId());

        assertNotNull(model.get().getParams());
        assertNotNull(model.get().getTimestamp());
        assertNotNull(model.get().getContext());

        // check params have been added, should show 2
        assertTrue(model.get().getParams().containsKey("ARG0"));
        assertTrue(model.get().getParams().containsKey("ARG1"));
        assertEquals(99.0, model.get().getParams().get("ARG0"));
        assertEquals(100.0, model.get().getParams().get("ARG1"));

        // Check the context
        assertNotNull(model.get().getContext().get("session-id"));
        assertEquals("ABC", model.get().getContext().get("session-id"));
    }

    @Test
    public void should_create_list_thread_context() {

        // Arrange

        // Act
        fooList.calc(199, 200);

        // Assert
        // Get the context - tests running on the same thread
        Optional<List<String>> list = listHandler.getThreadContext();

        assertEquals(5, list.get().size());
        assertEquals("1234567890", list.get().get(0)); // request id
        assertEquals("com.shedhack.thread.context.adapter.FooService.calc", list.get().get(2)); // method name
        assertEquals("{session-id=ABC}", list.get().get(3));
        assertEquals("{ARG0=199, ARG1=200}", list.get().get(4));
    }

    @Test
    public void should_create_simple_thread_context() {

        // Arrange

        // Act
        fooSimple.calc(199, 200);

        // Assert
        // Get the context - tests running on the same thread
        Optional<String> string = simpleHandler.getThreadContext();
        assertTrue(string.isPresent());
    }
}
