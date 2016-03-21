package com.shedhack.thread.context.handler;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Test to check the {@link SimpleThreadContextHandler}
 *
 * @author imamchishty
 */
public class SimpleThreadContextHandlerTest {

    private SimpleThreadContextHandler handler = new SimpleThreadContextHandler();

    @Before
    public void resetThreadName() {
        Thread.currentThread().setName("THREAD");
    }

    @Test
    public void should_create_context_with_valid_string() {

        // Act
        handler.setThreadContext("12345,methodName,ABCD1234");

        // Get the context
        Optional<String> response = handler.getThreadContext();

        // Assert
        assertEquals("12345,methodName,ABCD1234", response.get());
    }

    @Test
    public void shouldnt_have_context_with_empty_string() {

        // Act
        handler.setThreadContext("");

        // Get context
        Optional<String> response = handler.getThreadContext();

        // Assert
        assertTrue(response.isPresent());

        // The thread may have a valid name, but it shouldn't be the one we set it to.
        assertEquals("THREAD", response.get());
    }

    @Test
    public void shouldnt_have_context_with_null_string() {

        // Act
        handler.setThreadContext(null);

        // Get context
        Optional<String> response = handler.getThreadContext();

        // Assert
        assertTrue(response.isPresent());

        // The thread may have a valid name, but it shouldn't be the one we set it to.
        assertEquals("THREAD", response.get());
    }
}
