package se.hig.aod.lab2;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A double-linked list
 * 
 * @param <T>
 *            type to store
 * 
 * @author Viktor Hanstorp (ndi14vhp@student.hig.se)
 */
@SuppressWarnings("hiding")
public class LinkedList<T> implements ExtendedList<T>
{
    private ListNode first;

    private ListNode last;

    private int size = 0;

    @Override
    public boolean isEmpty()
    {
        return first == null;
    }

    @Override
    public void clear()
    {
        first = null;
        last = null;
        size = 0;
    }

    @Override
    public int numberOfElements()
    {
        return size;
    }

    /**
     * Insert multiple elements into the list from the front<br>
     * <br>
     * 
     * [toAdd + LinkedList]
     * 
     * @param first
     *            the first element to add
     * @param toAdd
     *            the elements to add
     */
    @SuppressWarnings("unchecked")
    public void insertFirst(T first, T... toAdd)
    {
        if (toAdd == null)
        {
            insertFirst(null);
            insertFirst(first);
            return;
        }
        else
        {
            for (int i = toAdd.length - 1; i >= 0; i--)
            {
                insertFirst(toAdd[i]);
            }
            insertFirst(first);
        }
    }

    /**
     * Insert multiple elements into the list from the back<br>
     * <br>
     * 
     * [LinkedList + toAdd]
     * 
     * @param first
     *            the first element to add
     * @param toAdd
     *            the elements to add
     */
    @SuppressWarnings("unchecked")
    public void insertLast(T first, T... toAdd)
    {
        if (toAdd == null)
        {
            insertLast(first);
            insertLast(null);
            return;
        }
        else
        {
            insertLast(first);

            for (int i = 0; i < toAdd.length; i++)
            {
                insertLast(toAdd[i]);
            }
        }
    }

    @Override
    public void insertFirst(T t)
    {
        ListNode node = new ListNode(t);
        if (isEmpty())
        {
            first = node;
            last = node;
            size = 1;
        }
        else
        {
            first.prepend(node);
        }
    }

    @Override
    public void insertLast(T t)
    {
        ListNode node = new ListNode(t);
        if (isEmpty())
        {
            first = node;
            last = node;
            size = 1;
        }
        else
        {
            last.append(node);
        }
    }

    @Override
    public T removeFirst()
    {
        if (first == null)
            throw new ListIsEmptyException("Can't remove first of empty list!");

        T target = first.data;
        first.remove();

        return target;
    }

    @Override
    public T removeLast()
    {
        if (last == null)
            throw new ListIsEmptyException("Can't remove last of empty list!");

        T target = last.data;
        last.remove();

        return target;
    }

    @Override
    public T getFirst()
    {
        if (first != null)
            return first.data;
        else
            throw new ListIsEmptyException("Can't get first of empty list!");
    }

    @Override
    public T getLast()
    {
        if (last != null)
            return last.data;
        else
            throw new ListIsEmptyException("Can't get last of empty list!");
    }

    @Override
    public boolean contains(T t)
    {
        return !search(t).isEmpty();
    }

    /**
     * Insert multiple elements
     * 
     * @param index
     *            the index to insert at
     * @param element
     *            the first element
     * @param elements
     *            the following elements
     * 
     * @see #insert(int, Object)
     */
    @SuppressWarnings("unchecked")
    public void insert(int index, T element, T... elements)
    {
        if (index < 0)
            index = size + index + 1;

        if (isEmpty())
        {
            if (index == -1)
                index = 0;

            if (index != 0)
                throw new IndexOutOfBoundsException("Can't insert element at invalid position");

            insertFirst(element, elements);
        }
        else
        {
            if (index == size)
                insertLast(element, elements);
            else
            {
                ListNode node;

                getNodeAt(index).prepend(node = new ListNode(element));

                if (elements == null)
                    node.append(new ListNode(null));
                else
                    for (T e : elements)
                        node.append(node = new ListNode(e));
            }
        }
    }

    @Override
    public void insert(int index, T element)
    {
        if (index < 0)
            index = size + index + 1;

        if (isEmpty())
        {
            if (index != 0)
            {
                throw new IndexOutOfBoundsException("Can't insert element at invalid position");
            }
            else
            {
                first = last = new ListNode(element);
                size = 1;
            }
        }
        else
        {
            if (index == size)
                insertLast(element);
            else
                getNodeAt(index).prepend(new ListNode(element));
        }
    }

    @Override
    public void remove(int index)
    {
        getNodeAt(index).remove();
    }

    @Override
    public T get(int index)
    {
        return getNodeAt(index).data;
    }

    ListNode getNodeAt(int index)
    {
        if (index < 0)
            index = size + index;

        if (isEmpty())
            throw new ListIsEmptyException("Empty list!");

        if (index >= size)
            throw new IndexOutOfBoundsException("Invalid index");

        if (index < 0)
            throw new IndexOutOfBoundsException("Invalid index");

        ListNode node = first;
        for (int i = 0; i < index; i++)
            node = node.next;

        return node;
    }

    @Override
    public void printList()
    {
        if (isEmpty())
        {
            System.out.println("List is empty");
            return;
        }

        StringBuilder builder = new StringBuilder();
        ListNode current = first;

        do
        {
            builder.append(current.data);
            if (current.next != null)
                builder.append(" , ");
        }
        while ((current = current.next) != null);

        System.out.println(builder.toString());
    }

    @Override
    public void printListR()
    {
        if (isEmpty())
        {
            System.out.println("List is empty");
            return;
        }

        StringBuilder builder = new StringBuilder();
        first.appendThisAndFollowing(builder);

        System.out.println(builder.toString());
    }

    @Override
    public void reversePrintList()
    {
        if (isEmpty())
        {
            System.out.println("List is empty");
            return;
        }

        StringBuilder builder = new StringBuilder();
        first.prependThisAndFollowing(builder);

        System.out.println(builder.toString());
    }

    /**
     * Search for an element in the list <br>
     * <br>
     * Example: Removing all 'g' characters from the list by using search
     * 
     * <pre>
     * <code>
     *  linkedList.search('g').remove();
     * </code>
     * </pre>
     * 
     * Example: Count instances of character 'a'
     * 
     * <pre>
     * <code>
     *  int count = linkedList.search('a').getSize();
     * </code>
     * </pre>
     * 
     * Example: Check if the list contains 'b'
     * 
     * <pre>
     * <code>
     *  boolean containsB = !linkedList.search('b').isEmpty();
     * </code>
     * </pre>
     * 
     * Example: Iterate over results
     * 
     * <pre>
     * <code>
     *  for(LinkedList<Integer>.Element element : testList.search(1000))
     *  {
     *      System.out.println(element);
     *  }
     * </code>
     * </pre>
     * 
     * @param element
     *            the element to search for
     * @return a {@link SearchResult} with the found elements
     * 
     */
    @SuppressWarnings("unchecked")
    public SearchResult search(T element)
    {
        if (isEmpty())
            return new SearchResult();
        else
        {
            Object[] root = new Object[2];
            Object[] curr = root;
            int count = 0;

            ListNode current = first;

            do
            {
                if (current.data == null)
                {
                    if (element == null)
                    {
                        curr[0] = current;
                        curr[1] = curr = new Object[2];
                        count++;
                    }
                }
                else
                {
                    if (current.data.equals(element))
                    {
                        curr[0] = current;
                        curr[1] = curr = new Object[2];
                        count++;
                    }
                }
            }
            while ((current = current.next) != null);

            curr = root;

            ListNode[] resultingNodes = new LinkedList.ListNode[count];
            for (int i = 0; i < count; i++)
            {
                resultingNodes[i] = (ListNode) curr[0];
                curr = (Object[]) curr[1];
            }

            return new SearchResult(resultingNodes);
        }
    }

    /**
     * Same as {@link #search(Object)} but recursive
     * 
     * @param element
     *            the element to search for
     * @return the result
     * 
     * @see #search(Object)
     */
    public SearchResult searchRecursive(T element)
    {
        class RecursiveSearch
        {
            T searchFor;

            RecursiveSearch(T element)
            {
                searchFor = element;
            }

            int count = 0;
            ListNode[] hits;

            @SuppressWarnings("unchecked")
            ListNode[] doSubSearch(ListNode current)
            {
                if (current == null)
                {
                    hits = new LinkedList.ListNode[count];
                }
                else
                {
                    int index = -1;

                    if (current.data == null)
                    {
                        if (searchFor == null)
                        {
                            index = count++;
                        }
                    }
                    else
                        if (current.data.equals(searchFor))
                        {
                            index = count++;
                        }

                    doSubSearch(current.next);

                    if (index != -1)
                        hits[index] = current;
                }
                return hits;
            }

            SearchResult doSearch()
            {
                return new SearchResult(doSubSearch(first));
            }
        }

        return new RecursiveSearch(element).doSearch();
    }

    /**
     * Get a element by index
     * 
     * @param index
     *            the index of the element to get
     * @throws ListIsEmptyException
     *             if list is empty
     * @return the element
     */
    public Element getElementAt(int index)
    {
        if (index < 0)
            index = size + index;

        return getNodeAt(index).getElement();
    }

    /**
     * Get a random element from this list
     * 
     * @throws ListIsEmptyException
     *             if list is empty
     * @return the element
     */
    public Element getRandomElement()
    {
        if (isEmpty())
            throw new ListIsEmptyException("Can't get random of empty list!");
        return getNodeAt((int) (Math.random() * size)).getElement();
    }

    /**
     * Results from a search operation on the list<br>
     * <br>
     * Found elements are in {@link Element wrappers} in the
     * {@link SearchResult#results results}<br>
     * <br>
     * It is possible to remove found elements by calling {@link Element#remove
     * remove} on the wrapper, or {@link SearchResult#remove remove} on this
     * {@link SearchResult} to remove all findings from the list<br>
     * <br>
     * For examples see {@link LinkedList#search(Object)}
     * 
     * 
     * @author Viktor Hanstorp (ndi14vhp@student.hig.se)
     */
    public class SearchResult implements Iterable<Element>
    {
        /**
         * The results of the search in the wrapper {@link Element}
         */
        public final Element[] results;

        @SuppressWarnings("unchecked")
        SearchResult()
        {
            this.results = new LinkedList.Element[0];
        }

        SearchResult(Element[] results)
        {
            this.results = results;
        }

        /**
         * Removes all elements found in this search from the list
         */
        public void remove()
        {
            for (Element e : results)
                e.remove();
        }

        /**
         * Get a found element by index
         * 
         * @param index
         *            the index in the results array
         * @return the found element
         * @throws IndexOutOfBoundsException
         *             on invalid index
         */
        public Element get(int index)
        {
            if (index < 0)
                index = results.length - 1 - index;

            if (index < 0 || index >= results.length)
                throw new IndexOutOfBoundsException("Using an invalid index");

            return results[index];
        }

        /**
         * Check if there are no findings in this search
         * 
         * @return true if there are no findings, false if there is
         */
        public boolean isEmpty()
        {
            return results.length == 0;
        }

        /**
         * Get the number of findings
         * 
         * @return the number of findings
         */
        public int getSize()
        {
            return results.length;
        }

        @SuppressWarnings({ "unchecked", "rawtypes" })
        SearchResult(LinkedList.ListNode[] nodes)
        {
            results = new LinkedList.Element[nodes.length];
            for (int i = 0; i < nodes.length; i++)
            {
                results[i] = nodes[i].getElement();
            }
        }

        @Override
        public Iterator<Element> iterator()
        {
            return new SearchResultIterator();
        }

        class SearchResultIterator implements Iterator<Element>
        {
            int index = 0;

            @Override
            public boolean hasNext()
            {
                return !isEmpty() && index < getSize();
            }

            @Override
            public Element next()
            {
                if (!hasNext())
                    throw new NoSuchElementException();

                return results[index++];
            }
        }
    }

    /**
     * Wrapper for a ListNode that only expose the data it is holding and a
     * remove function
     * 
     * 
     * @author Viktor Hanstorp (ndi14vhp@student.hig.se)
     */
    public class Element
    {
        /**
         * The data this element represents
         */
        public final T data;
        private ListNode node;

        Element(ListNode node)
        {
            data = node.data;
            this.node = node;
        }

        /**
         * Remove the node that this element lays in from the list
         */
        public void remove()
        {
            node.remove();
        }

        @Override
        public String toString()
        {
            return data == null ? "null" : data.toString();
        }
    }

    @Override
    public String toString()
    {
        if (isEmpty())
            return "";
        else
            return first.appendThisAndFollowing(null).toString();
    }

    class ListNode
    {
        final T data;
        ListNode next;
        ListNode prev;

        ListNode(T data)
        {
            this.data = data;
            size++;
        }

        void append(ListNode other)
        {
            other.next = next;
            if (next != null)
                next.prev = other;

            other.prev = this;
            next = other;

            if (last == this)
                last = other;
        }

        void prepend(ListNode other)
        {
            other.next = this;
            other.prev = prev;

            if (prev != null)
                prev.next = other;
            prev = other;

            if (first == this)
                first = other;
        }

        void remove()
        {
            if (prev != null)
                prev.next = next;

            if (next != null)
                next.prev = prev;

            if (first == this)
                first = next;

            if (last == this)
                last = prev;

            next = prev = null;

            size--;
        }

        Element getElement()
        {
            return new Element(this);
        }

        StringBuilder appendThisAndFollowing(StringBuilder builder)
        {
            if (builder == null)
                builder = new StringBuilder();

            builder.append(data);
            if (next != null)
            {
                builder.append(" , ");
                next.appendThisAndFollowing(builder);
            }

            return builder;
        }

        StringBuilder prependThisAndFollowing(StringBuilder builder)
        {
            if (builder == null)
                builder = new StringBuilder();

            if (next != null)
            {
                next.prependThisAndFollowing(builder);
                builder.append(" , ");
            }
            builder.append(data);

            return builder;
        }
    }
}
