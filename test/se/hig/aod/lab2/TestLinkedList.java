package se.hig.aod.lab2;

import static org.junit.Assert.*;
import static se.hig.aod.lab2.T.*;

import java.util.ArrayDeque;
import java.util.Deque;

import org.junit.Before;
import org.junit.Test;

/**
 * Testing list
 * 
 * @author Viktor Hanstorp (ndi14vhp@student.hig.se)
 */
public class TestLinkedList
{
    /**
     * The list we test on
     */
    public LinkedList<Integer> testList;

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
        testList.getElementAt(3).remove();
        assertEquals("Not correct count", 5, testList.numberOfElements());
        testList.getElementAt(2).remove();
        assertEquals("Not correct count", 4, testList.numberOfElements());
    }

    /**
     * Lets check if getNodeAt works
     */
    @Test
    public void testGetElementAt()
    {
        for (int i = 0; i < 10; i++)
        {
            testList.insertLast(i);
        }

        for (int i = 0; i < 10; i++)
        {
            assertEquals("Gets wrong value!", testList.getElementAt(i).data, (Integer) i);
        }

        for (int i = -1; i > -10; i--)
        {
            assertEquals("Gets wrong value from neg index!", testList.getElementAt(i).data, (Integer) (10 + i));
        }
    }
    
    /**
     * Lets check if getNodeAt works
     */
    @Test
    public void testGetNodeAt()
    {
        for (int i = 0; i < 10; i++)
        {
            testList.insertLast(i);
        }

        for (int i = 0; i < 10; i++)
        {
            assertEquals("Gets wrong value!", testList.getNodeAt(i).data, (Integer) i);
        }

        for (int i = -1; i > -10; i--)
        {
            assertEquals("Gets wrong value from neg index!", testList.getNodeAt(i).data, (Integer) (10 + i));
        }
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

    // Begin Index stuff

    /**
     * Test get by index
     */
    @Test
    public void testGet()
    {
        for (int i = 1; i < 10; i++)
            testList.insertLast(i);
        testList.insertFirst(0);
        testList.insertLast(10);

        for (int i = 0; i <= 10; i++)
            assertEquals("Not correct get", (Integer) i, testList.get(i));

        for (int i = 1; i <= 11; i++)
            assertEquals("Not correct negative get", (Integer) (10 - i + 1), testList.get(-i));

        assertThrows(ExtendedList.IndexOutOfBoundsException.class, () ->
        {
            testList.get(10000);
        });

        assertThrows(ExtendedList.IndexOutOfBoundsException.class, () ->
        {
            testList.get(-10000);
        });
    }

    /**
     * Test insert at index
     */
    @Test
    public void testInsert()
    {
        testList.insertLast(3);
        testList.insertLast(5);
        testList.insertLast(8);
        testList.insertLast(9);

        testList.insert(-5, 1);
        testList.insert(-6, 0);
        testList.insert(2, 2);
        testList.insert(4, 4);
        testList.insert(6, 6);
        testList.insert(-3, 7);
        testList.insert(-1, 10);
        
        assertThrows(ExtendedList.IndexOutOfBoundsException.class, () ->
        {
            testList.insert(-100, 0);
        });
        
        assertThrows(ExtendedList.IndexOutOfBoundsException.class, () ->
        {
            testList.insert(400, 10);
        });

        for (int i = 0; i <= 10; i++)
        {
            assertEquals("Not correct get", (Integer) i, testList.get(i));
        }
    }

    /**
     * Test insert multiple at index
     */
    @Test
    public void testMultiInsert()
    {
        testList.insertLast(1);
        testList.insertLast(4, 5);
        testList.insertLast(9, 10, 11);

        testList.insert(0, 0);
        testList.insert(2, 2, 3);
        testList.insert(-4, 6, 7, 8);

        for (int i = 0; i <= 10; i++)
            assertEquals("Not correct get", (Integer) i, testList.get(i));
    }

    /**
     * Test remove at index
     */
    @Test
    public void testRemove()
    {
        for (int i = 0; i <= 10; i++)
            testList.insertLast(i);

        testList.remove(0);
        testList.remove(-1);

        for (int i = 0; i < 9; i++)
            assertEquals("Not correctly removed", (Integer) (i + 1), testList.get(i));

        testList.insertFirst(0);
        testList.insertLast(10);

        testList.remove(7);
        testList.remove(5);

        int index = 0;
        for (int i = 0; i <= 10; i++)
            if (i != 5 && i != 7)
                assertEquals("Not correctly removed", (Integer) i, testList.get(index++));

        assertThrows(ExtendedList.IndexOutOfBoundsException.class, () ->
        {
            testList.remove(10000);
        });

        assertThrows(ExtendedList.IndexOutOfBoundsException.class, () ->
        {
            testList.remove(-10000);
        });
    }

    /**
     * Lets check if numberOfElements works for indexed operators
     */
    @Test
    public void testIndexedNumberOfElements()
    {
        assertEquals("Not empty when empty", 0, testList.numberOfElements());
        for (int i = 0; i < 10; i++)
        {
            testList.insert(0, i);
            assertEquals("Not correct count", i + 1, testList.numberOfElements());
        }

        testList.remove(0);
        assertEquals("Not correct count", 9, testList.numberOfElements());
        testList.remove(0);
        assertEquals("Not correct count", 8, testList.numberOfElements());
        testList.remove(-1);
        assertEquals("Not correct count", 7, testList.numberOfElements());
        testList.remove(-1);
        assertEquals("Not correct count", 6, testList.numberOfElements());

        for (int i = 0; i < 4; i++)
        {
            testList.insert(0, i);
            assertEquals("Not correct count", i + 7, testList.numberOfElements());
        }

        testList.remove(0);
        assertEquals("Not correct count", 9, testList.numberOfElements());
        testList.remove(0);
        assertEquals("Not correct count", 8, testList.numberOfElements());
        testList.remove(-1);
        assertEquals("Not correct count", 7, testList.numberOfElements());
        testList.remove(-1);
        assertEquals("Not correct count", 6, testList.numberOfElements());
    }

    // End Index stuff

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

    /**
     * Test search
     */
    @Test
    public void testSearch()
    {
        testList.insertFirst(1000);
        populate();
        testList.insertLast(1000);

        assertEquals("Can't find the elements", 2, testList.search(1000).getSize());
    }

    /**
     * Test to use search for removing elements
     */
    @Test
    public void testSearchRemove()
    {
        testList.insertFirst(1000);
        populate();
        testList.insertLast(1000);

        testList.search(1000).get(0).remove();
        assertEquals("Element not removed!", 1, testList.search(1000).getSize());
        testList.search(1000).get(0).remove();
        assertEquals("Element not removed!", 0, testList.search(1000).getSize());
    }

    /**
     * Test to use search for removing elements (index out of bounds)
     */
    @Test(expected = LinkedList.IndexOutOfBoundsException.class)
    public void testSearchRemoveTooMuch()
    {
        testList.insertFirst(1000);
        populate();
        testList.insertLast(1000);

        testList.search(1000).get(0).remove();
        assertEquals("Element not removed!", 1, testList.search(1000).getSize());
        testList.search(1000).get(0).remove();
        assertEquals("Element not removed!", 0, testList.search(1000).getSize());
        testList.search(1000).get(0).remove();
    }

    /**
     * Test using search to remove elements
     */
    @Test
    public void testSearchRemoveAll()
    {
        testList.insertFirst(1000);
        populate();
        testList.insertLast(1000);

        testList.search(1000).remove();

        assertEquals("Elements not removed!", 0, testList.search(1000).getSize());
    }

    /**
     * Test iterate
     */
    @Test
    public void testSearchIterate()
    {
        Deque<Integer> stack = new ArrayDeque<Integer>();
        stack.addLast(1111);
        stack.addLast(2222);
        populate();
        stack.addLast(3333);

        for (LinkedList<Integer>.Element element : testList.search(1000))
        {
            assertEquals("Incorrect search result!", stack.pop(), element.data);
        }
    }
}
