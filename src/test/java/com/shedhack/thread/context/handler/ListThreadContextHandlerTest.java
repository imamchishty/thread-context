package com.shedhack.thread.context.handler;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Test to check the {@link ListThreadContextHandler}
 *
 * @author imamchishty
 */
public class ListThreadContextHandlerTest {

    private ListThreadContextHandler handler = new ListThreadContextHandler();

    @Before
    public void resetThreadName() {
        Thread.currentThread().setName("THREAD");
    }

    @Test
    public void create_context_with_valid_list() {

        // Arrange
        List<String> input = Arrays.asList("a", "b", "c", "d", "e");

        // Act
        handler.setThreadContext(input);

        // Get the context
        Optional<List<String>> output = handler.getThreadContext();

        // Assert
        assertEquals(input.size(), output.get().size());

        for(int i=0; i<input.size(); i++) {
            assertEquals(input.get(i), output.get().get(i));
        }
    }

    @Test
    public void creating_context_with_a_null_list() {

        // Act
        handler.setThreadContext(null);
        Optional<List<String>> output = handler.getThreadContext();

        // Assert - thread name always usually has a name (in our test default is THREAD)
        assertTrue(output.isPresent());
        assertEquals(1, output.get().size());
        assertEquals("THREAD", output.get().get(0));
    }

    @Test
    public void creating_context_with_an_empty_list() {

        // Act
        handler.setThreadContext(Collections.EMPTY_LIST);
        Optional<List<String>> output = handler.getThreadContext();

        // Assert - thread name always usually has a name (in our test default is THREAD)
        assertTrue(output.isPresent());
        assertEquals(1, output.get().size());
        assertEquals("THREAD", output.get().get(0));
    }
}
