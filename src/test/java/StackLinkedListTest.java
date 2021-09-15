import model.stack.StackLinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.*;

class StackLinkedListTest {
    private StackLinkedList<Object> stack;
    private String test = "test";

    @BeforeEach
    void setUp() {
        stack = new StackLinkedList<>();
    }

    @Test
    void isEmpty() {
        assertTrue(stack.empty());
        stack.push(new Object());
        assertFalse(stack.empty());
        stack.pop();
        assertTrue(stack.empty());
    }

    @Test
    void exceptionWhenPopEmptyStack() {
        assertThrows(EmptyStackException.class, stack::pop);
    }

    @Test
    void exceptionWhenPeekEmptyStack() {
        assertThrows(EmptyStackException.class, stack::peek);
    }

    @Test
    void pop() {
        stack.push(test);
        assertEquals(test, stack.pop());
        assertTrue(stack.empty());
    }

    @Test
    void peek() {
        stack.push(test);
        assertEquals(test, stack.peek());
        assertFalse(stack.empty());
    }

    @Test
    void size() {
        assertTrue(stack.size() == 0);
        stack.push(test);
        assertTrue(stack.size() == 1);
        stack.peek();
        assertTrue(stack.size() == 1);
        stack.pop();
        assertTrue(stack.size() == 0);
    }

    @Test
    void clear() {
        stack.push(7);
        stack.push(9);
        stack.clear();
        assertTrue(stack.size() == 0);
    }
}