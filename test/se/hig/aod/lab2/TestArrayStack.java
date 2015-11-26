package se.hig.aod.lab2;

import static org.junit.Assert.*;
import static se.hig.aod.lab2.T.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Testing ArrayStack
 * 
 * @author Viktor Hanstorp (ndi14vhp@student.hig.se)
 */
public class TestArrayStack
{
    /**
     * The stack to test
     */
    public ArrayStack<Character> testStack;

    /**
     * Characters to test
     */
    char[] fixture = { 'a', 'b', 'c', 'd' };

    /**
     * Setting up the stack
     */
    @Before
    public void setUp()
    {
        testStack = new ArrayStack<Character>(10);
    }

    /**
     * A stack should be empty when created, lets check
     */
    @Test
    public void testNewStackIsEmpty()
    {
        assertTrue("A new instance cannot contain any item!", testStack.isEmpty());
    }

    /**
     * A stack should not be empty after we put something in it, lets check
     */
    @Test
    public void testStackWithItemNotEmpty()
    {
        testStack.push('a');
        assertFalse("Containing an item but is empty!", testStack.isEmpty());
    }

    /**
     * The stack should throw StackEmptyException if you try to destack from a
     * empty stack, lets check
     */
    @Test(expected = ArrayStack.StackIsEmptyException.class)
    public void testPopOnEmptyStack()
    {
        testStack.pop();
    }

    /**
     * The stack should be empty if you remove stuff you put in, lets check
     */
    @Test
    public void testStackIsEmptyAfterPop()
    {
        testStack.push('a');
        testStack.pop();
        assertTrue("Not empty after pop!", testStack.isEmpty());
    }

    /**
     * The stack should throw StackEmptyException if you try to peek on a empty
     * stack, lets check
     */
    @Test(expected = ArrayStack.StackIsEmptyException.class)
    public void testTopOnEmptyStack()
    {
        testStack.top();
    }

    /**
     * The stack's 'front' method should give us a peek on the first element,
     * lets check
     */
    @Test
    public void testTop()
    {
        for (Character c : fixture)
        {
            testStack.push(c);
            assertEquals("Incorrect top!", new Character(c), testStack.top());
        }
        testStack.pop();
        assertEquals("Incorrect top after pop!", new Character(fixture[fixture.length - 2]), testStack.top());
    }

    /**
     * The stack should be First-In Last-Out, lets check
     */
    @Test
    public void testPopOrder()
    {
        Character[] expected = new Character[fixture.length];

        for (int i = 0; i < fixture.length; i++)
        {
            char c = fixture[i];

            testStack.push(c);
            expected[fixture.length - (i + 1)] = c;
        }

        for (Character c : expected)
            assertEquals("Incorrect pop order!", c, testStack.pop());

        assertTrue("Not empty after pops!", testStack.isEmpty());
    }

    /**
     * The stack should be empty when cleared (even if it already was empty),
     * lets check
     */
    @Test
    public void testClearEmptyStack()
    {
        testStack.clear();
        assertTrue("Clear seems to be inversed?", testStack.isEmpty());
    }

    /**
     * The stack should be empty when cleared, lets check
     */
    @Test
    public void testClearPopulatedStack()
    {
        for (Character c : fixture)
            testStack.push(c);

        testStack.clear();

        assertTrue("Not empty after clear!", testStack.isEmpty());
    }

    /**
     * Test filling the stack
     */
    @Test
    public void testFillStack()
    {
        for (int i = 0; i < 10; i++)
        {
            assertFalse("Not-full stack is full!", testStack.isFull());
            testStack.push((char) (i + 48));
        }

        assertTrue("Full stack is not full!", testStack.isFull());

        assertThrows(ArrayStack.StackIsFullException.class, () ->
        {
            testStack.push('n');
        });
        
        testStack.pop();
        assertFalse("Not-full stack is full!", testStack.isFull());
        testStack.pop();
        testStack.pop();
        
        testStack.push('a');
        testStack.push('b');
        testStack.push('c');
        
        assertTrue("Full stack is not full!", testStack.isFull());

        assertThrows(ArrayStack.StackIsFullException.class, () ->
        {
            testStack.push('n');
        });
    }
}
