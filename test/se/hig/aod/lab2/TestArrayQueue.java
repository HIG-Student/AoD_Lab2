package se.hig.aod.lab2;

import static org.junit.Assert.*;
import static se.hig.aod.lab2.T.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Testing ArrayQueue
 * 
 * @author Viktor Hanstorp (ndi14vhp@student.hig.se)
 */
public class TestArrayQueue
{
    /**
     * The queue to test
     */
    public ArrayQueue<Character> testQueue;

    /**
     * Characters to test
     */
    char[] fixture = { 'a', 'b', 'c', 'd' };

    /**
     * Make queue
     */
    @Before
    public void setUp()
    {
        testQueue = new ArrayQueue<Character>(10);
    }

    /**
     * A queue should be empty when created, lets check
     */
    @Test
    public void testNewQueueIsEmpty()
    {
        assertTrue("A new instance cannot contain any item!", testQueue.isEmpty());
    }

    /**
     * A queue should not be empty after we put something in it, lets check
     */
    @Test
    public void testQueueWithItemNotEmpty()
    {
        testQueue.enqueue('a');
        assertFalse("Containing an item but is empty!", testQueue.isEmpty());
    }

    /**
     * The queue should throw QueueEmptyException if you try to dequeue from a
     * empty queue, lets check
     */
    @Test(expected = ArrayQueue.QueueIsEmptyException.class)
    public void testDequeueOnEmptyQueue()
    {
        testQueue.dequeue();
    }

    /**
     * The queue should be empty if you remove stuff you put in, lets check
     */
    @Test
    public void testQueueIsEmptyAfterDequeue()
    {
        testQueue.enqueue('a');
        testQueue.dequeue();
        assertTrue("Not empty after dequeue!", testQueue.isEmpty());
    }

    /**
     * The queue should throw QueueEmptyException if you try to peek on a empty
     * queue, lets check
     */
    @Test(expected = ArrayQueue.QueueIsEmptyException.class)
    public void testFrontOnEmptyQueue()
    {
        testQueue.getFront();
    }

    /**
     * The queue's 'front' method should give us a peek on the first element,
     * lets check
     */
    @Test
    public void testFront()
    {
        for (Character c : fixture)
        {
            testQueue.enqueue(c);
            assertEquals("Incorrect getFront!", new Character(fixture[0]), testQueue.getFront());
        }
        testQueue.dequeue();
        assertEquals("Incorrect getFront after dequeue!", new Character(fixture[1]), testQueue.getFront());
    }

    /**
     * The queue should be First-In Last-Out, lets check
     */
    @Test
    public void testDequeueOrder()
    {
        for (int i = 0; i < fixture.length; i++)
        {
            char c = fixture[i];
            testQueue.enqueue(c);
        }

        for (Character c : fixture)
            assertEquals("Incorrect dequeue order!", c, testQueue.dequeue());

        assertTrue("Not empty after dequeues!", testQueue.isEmpty());
    }

    /**
     * The queue should be empty when cleared (even if it already was empty),
     * lets check
     */
    @Test
    public void testClearEmptyQueue()
    {
        testQueue.clear();
        assertTrue("Clear seems to be inversed?", testQueue.isEmpty());
    }

    /**
     * The queue should be empty when cleared, lets check
     */
    @Test
    public void testClearPopulatedQueue()
    {
        for (Character c : fixture)
            testQueue.enqueue(c);

        testQueue.clear();

        assertTrue("Not empty after clear!", testQueue.isEmpty());
    }

    /**
     * Test filling the queue (including a wrap-around)
     */
    @Test
    public void testFillQueue()
    {
        for (int i = 0; i < 10; i++)
        {
            assertFalse("Not-full stack is full!", testQueue.isFull());
            testQueue.enqueue((char) (i + 48));
        }

        assertTrue("Full stack is not full!", testQueue.isFull());

        assertThrows(ArrayQueue.QueueIsFullException.class, () ->
        {
            testQueue.enqueue('n');
        });

        testQueue.dequeue();
        assertFalse("Not-full stack is full!", testQueue.isFull());
        testQueue.dequeue();
        testQueue.dequeue();

        testQueue.enqueue('a');
        testQueue.enqueue('b');
        testQueue.enqueue('c');

        assertTrue("Full stack is not full!", testQueue.isFull());

        assertThrows(ArrayQueue.QueueIsFullException.class, () ->
        {
            testQueue.enqueue('n');
        });
    }
}
