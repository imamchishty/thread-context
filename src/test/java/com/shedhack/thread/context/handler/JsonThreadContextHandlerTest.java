package com.shedhack.thread.context.handler;

import com.shedhack.thread.context.model.DefaultThreadContextModel;
import com.shedhack.thread.context.model.ThreadContextModel;
import org.junit.Test;

import java.util.Date;
import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Test to check the {@link JsonThreadContextHandler}
 *
 * @author imamchishty
 */
public class JsonThreadContextHandlerTest {

    private JsonThreadContextHandler handler = new JsonThreadContextHandler();

    @Test
    public void should_create_thread_context() {

        // Arrange
        ThreadContextModel model = new DefaultThreadContextModel();
        model.setId("ABCD12345");
        model.setMethodName("stringConcat");
        model.setTimestamp(new Date());
        model.addContext("sessionid", "12345");
        model.addContext("xsrf", "86AV837");
        model.addParam("x", "one");
        model.addParam("y", "two");

        // Act
        handler.setThreadContext(model);

        // Get the context
        Optional<ThreadContextModel> response = handler.getThreadContext();

        // Assert
        assertEquals("ABCD12345", response.get().getId());
        assertEquals("stringConcat", response.get().getMethodName());
        assertNotNull(response.get().getTimestamp());

        // check context
        assertTrue(response.get().getContext().containsKey("xsrf"));
        assertTrue(response.get().getContext().containsKey("sessionid"));
        assertEquals("86AV837", response.get().getContext().get("xsrf"));
        assertEquals("12345", response.get().getContext().get("sessionid"));

        // check params
        assertTrue(response.get().getParams().containsKey("x"));
        assertTrue(response.get().getParams().containsKey("y"));
        assertEquals("one", response.get().getParams().get("x"));
        assertEquals("two", response.get().getParams().get("y"));
    }

    @Test
    public void shouldnt_create_context_with_null_model() {

        // Act
        handler.setThreadContext(null);

        // Get the context
        Optional<ThreadContextModel> model = handler.getThreadContext();

        // Assert
        assertFalse(model.isPresent());
    }

}
