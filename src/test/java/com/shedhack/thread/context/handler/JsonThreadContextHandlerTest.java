package com.shedhack.thread.context.handler;

import com.shedhack.thread.context.model.ThreadContextModel;
import junit.framework.Assert;
import org.junit.Test;

import java.util.Optional;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
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
        ThreadContextModel.Builder builder = new ThreadContextModel.Builder();

        ThreadContextModel model = builder.withMethodName("stringConcat").withDefaultDate()
                .withContext("sessionid", "12345").withContext("xsrf", "86AV837")
                .withId("ABCD12345").withParam("x", "one").withParam("y", "two")
                .build();

        // Act
        handler.setThreadContext(model);

        // Get the context
        Optional<ThreadContextModel> createdModel = handler.getThreadContext();

        // Assert
        assertEquals("ABCD12345", createdModel.get().getId());
        assertEquals("stringConcat", createdModel.get().getMethodName());
        assertNotNull(createdModel.get().getTimestamp());

        // check context
        assertTrue(createdModel.get().getContext().containsKey("xsrf"));
        assertTrue(createdModel.get().getContext().containsKey("sessionid"));
        assertEquals("86AV837", createdModel.get().getContext().get("xsrf"));
        assertEquals("12345", createdModel.get().getContext().get("sessionid"));

        // check params
        assertTrue(createdModel.get().getParams().containsKey("x"));
        assertTrue(createdModel.get().getParams().containsKey("y"));
        assertEquals("one", createdModel.get().getParams().get("x"));
        assertEquals("two", createdModel.get().getParams().get("y"));
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
