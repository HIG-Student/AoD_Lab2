package se.hig.aod.lab2;

import static org.junit.Assert.*;
import static se.hig.aod.lab2.T.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Testing list
 * 
 * @author Viktor Hanstorp (ndi14vhp@student.hig.se)
 */
public class TestList
{
    /**
     * The list we test on
     */
    public List<Integer> testList;

    /**
     * Some ints we tests
     */
    public Integer[] testInts = { 1, 2, 6, 7, 9, 3, 5, 1 };

    /**
     * Populate the list with ints
     */
    public void populate()
    {
        for (Integer i : testInts)
            testList.insertLast(i);
    }

    /**
     * Set up the list
     */
    @Before
    public void setUp()
    {
        testList = new LinkedList<Integer>();
    }

    /**
     * The list should be empty after creation, lets check
     */
    @Test
    public void testConstruct()
    {
        assertTrue("New list is not empty!", testList.isEmpty());
    }

    /**
     * Lets check if is empty works
     */
    @Test
    public void testIsEmpty()
    {
        assertTrue("New list is not empty!", testList.isEmpty());
        populate();
        assertFalse("List with elements is empty!", testList.isEmpty());
        testList.clear();
        assertTrue("Empty list is not empty!", testList.isEmpty());
    }

    /**
     * Lets check if clear works
     */
    @Test
    public void testClear()
    {
        populate();
        testList.clear();
        assertTrue("Clear does not empty list!", testList.isEmpty());
    }

    /**
     * Lets check if numberOfElements works
     */
    @Test
    public void testNumberOfElements()
    {
        assertEquals("Not empty when empty", 0, testList.numberOfElements());
        for (int i = 0; i < 10; i++)
        {
            testList.insertLast(i);
            assertEquals("Not correct count", i + 1, testList.numberOfElements());
        }

        testList.removeFirst();
        assertEquals("Not correct count", 9, testList.numberOfElements());
        testList.removeFirst();
        assertEquals("Not correct count", 8, testList.numberOfElements());
        testList.removeLast();
        assertEquals("Not correct count", 7, testList.numberOfElements());
        testList.removeLast();
        assertEquals("Not correct count", 6, testList.numberOfElements());

        for (int i = 0; i < 4; i++)
        {
            testList.insertFirst(i);
            assertEquals("Not correct count", i + 7, testList.numberOfElements());
        }

        testList.removeFirst();
        assertEquals("Not correct count", 9, testList.numberOfElements());
        testList.removeFirst();
        assertEquals("Not correct count", 8, testList.numberOfElements());
        testList.removeLast();
        assertEquals("Not correct count", 7, testList.numberOfElements());
        testList.removeLast();
        assertEquals("Not correct count", 6, testList.numberOfElements());
    }

    /**
     * Lets test if insertFirst works
     */
    @Test
    public void testInsertFirst()
    {
        for (Integer i : testInts)
        {
            testList.insertFirst(i);
            assertEquals("Not inserted first!", i, testList.getFirst());
        }
    }

    /**
     * Lets test if insertLast works
     */
    @Test
    public void testInsertLast()
    {
        for (Integer i : testInts)
        {
            testList.insertLast(i);
            assertEquals("Not inserted last!", i, testList.getLast());
        }
    }

    /**
     * Lets test if removeFirst works
     */
    @Test
    public void testRemoveFirst()
    {
        populate();
        for (int i = 0; i < testInts.length; i++)
        {
            assertEquals("First not correct", testInts[i], testList.removeFirst());
            if (i < testInts.length - 1)
                assertEquals("Remaining list is incorrect", testInts[i + 1], testList.getFirst());
            else
                assertTrue("Not empty after removal!", testList.isEmpty());
        }
    }

    /**
     * Lets test if removeLast works
     */
    @Test
    public void testRemoveLast()
    {
        populate();
        for (int i = 0; i < testInts.length; i++)
        {
            assertEquals("Last not correct", testInts[testInts.length - 1 - i], testList.removeLast());
            if (i < testInts.length - 1)
                assertEquals("Remaining list is incorrect", testInts[testInts.length - 2 - i], testList.getLast());
            else
                assertTrue("Not empty after removal!", testList.isEmpty());
        }
    }

    /**
     * Lets test if getFirst works
     */
    @Test
    public void testGetFirst()
    {
        populate();
        for (int i = 0; i < testInts.length; i++)
        {
            assertEquals("First not correct", testInts[i], testList.getFirst());
            testList.removeFirst();
        }
    }

    /**
     * Lets test if getLast works
     */
    @Test
    public void testGetLast()
    {
        populate();
        for (int i = 0; i < testInts.length; i++)
        {
            assertEquals("Last not correct", testInts[testInts.length - 1 - i], testList.getLast());
            testList.removeLast();
        }
    }

    /**
     * Lets test id contains works
     */
    @Test
    public void testContains()
    {
        for (int i = 0; i < testInts.length; i++)
        {
            assertFalse("Finding elements in empty list!", testList.contains(testInts[i]));
        }

        populate();

        for (int i = 0; i < testInts.length; i++)
        {
            assertTrue("Can't find element!", testList.contains(testInts[i]));
        }

        for (int i = 10; i < 20; i++)
        {
            assertFalse("False findings!", testList.contains(i));
        }
    }

    /**
     * LinkedList.getFirst() should throw error if the list is empty, lets check
     */
    @Test(expected = ExtendedList.ListIsEmptyException.class)
    public void testGetFirstOnEmpty()
    {
        testList.getFirst();
    }

    /**
     * LinkedList.removeFirst() should throw error if the list is empty, lets
     * check
     */
    @Test(expected = ExtendedList.ListIsEmptyException.class)
    public void testRemoveFirstOnEmpty()
    {
        testList.removeFirst();
    }

    /**
     * LinkedList.getLast() should throw error if the list is empty, lets check
     */
    @Test(expected = ExtendedList.ListIsEmptyException.class)
    public void testGetLastOnEmpty()
    {
        testList.getLast();
    }

    /**
     * LinkedList.removeLast() should throw error if the list is empty, lets
     * check
     */
    @Test(expected = ExtendedList.ListIsEmptyException.class)
    public void testRemoveLastOnEmpty()
    {
        testList.removeLast();
    }

    /**
     * The LinkedList.printList() and LinkedList.printListR() should print the
     * same thing, lets check
     */
    @Test
    public void testPrint()
    {
        populate();

        String resultIterative = checkSystemOut(() -> testList.printList());
        String resultRecursive = checkSystemOut(() -> testList.printListR());

        assertEquals("Recursive and iterative should give same result!", resultIterative, resultRecursive);
    }

    /**
     * Test if bad things happens if we insert null
     */
    @Test
    public void testNullElements()
    {
        testList.insertFirst(null);
        assertEquals("Should be null", null, testList.getFirst());
        assertEquals("Should be null", null, testList.removeFirst());

        testList.insertLast(null);
        assertEquals("Should be null", null, testList.getLast());
        assertEquals("Should be null", null, testList.removeLast());

        testList.insertFirst(null);
        populate();
        testList.insertLast(null);

        testList.insertFirst(null);
        testList.insertLast(null);

        assertTrue("List does contain null!", testList.contains(null));

        checkSystemOut(() ->
        {
            testList.printList();
            testList.printListR();
            testList.reversePrintList();
        });
    }
}
