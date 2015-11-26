package se.hig.aod.lab2;

/**
 * A double-linked list
 * 
 * @author Viktor Hanstorp (ndi14vhp@student.hig.se)
 */
public class LinkedList<T> implements ExtendedList<T>
{
    /**
     * The first node in this list
     */
    public ListNode first;
    /**
     * The last node in this list
     */
    public ListNode last;

    public int size = 0;

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

    @Override
    public void insertFirst(T t)
    {
        ListNode node = new ListNode(t);
        if (isEmpty())
        {
            first = node;
            last = node;
        }
        else
        {
            first.prev = node;
            node.next = first;
            first = node;
        }
        size++;
    }

    @Override
    public void insertLast(T t)
    {
        ListNode node = new ListNode(t);
        if (isEmpty())
        {
            first = node;
            last = node;
        }
        else
        {
            last.next = node;
            node.prev = last;
            last = node;
        }
        size++;
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

    @Override
    public void insert(int index, T element)
    {
        if (index < 0)
            index = size + index;

        if (index >= size)
            insertLast(element);
        else
            if (index <= 0)
                insertFirst(element);
            else
                if (index >= size - 1)
                    insertLast(element);
                else
                {
                    ListNode node = first;
                    for (int i = 0; i < index; i++)
                        node = node.next;

                    ListNode newNode = new ListNode(element);

                    newNode.next = node;
                    newNode.prev = node.prev;
                    if (node.prev != null)
                        node.prev.next = newNode;
                    node.prev = newNode;

                    size++;
                }
    }

    @Override
    public void remove(int index)
    {
        if (index < 0)
            index = size + index;

        if (index >= size)
            throw new IndexOutOfBoundsException("Can't get element at invalid position");
        else
            if (index < 0)
                throw new IndexOutOfBoundsException("Can't get element at invalid position");
            else
            {
                ListNode node = first;
                for (int i = 0; i < index; i++)
                    node = node.next;

                node.remove();
            }
    }

    @Override
    public T get(int index)
    {
        if (index < 0)
            index = size + index;

        if (index >= size)
            throw new IndexOutOfBoundsException("Can't get element at invalid position");
        else
            if (index < 0)
                throw new IndexOutOfBoundsException("Can't get element at invalid position");
            else
            {
                ListNode node = first;
                for (int i = 0; i < index; i++)
                    node = node.next;

                return node.data;
            }
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
        ListNode current = last;

        do
        {
            builder.append(current.data);
            if (current.prev != null)
                builder.append(" , ");
        }
        while ((current = current.prev) != null);

        System.out.println(builder.toString());
    }

    ListNode[] getNodes()
    {
        @SuppressWarnings("unchecked")
        ListNode[] nodes = new LinkedList.ListNode[numberOfElements()];

        if (nodes.length == 0)
            return nodes;

        ListNode current = first;

        int i = 0;
        do
        {
            nodes[i++] = current;
        }
        while ((current = current.next) != null);

        return nodes;
    }

    public SearchResult search(T element)
    {
        if (isEmpty())
            return new SearchResult();
        else
        {
            LinkedList<ListNode> results = new LinkedList<ListNode>();

            ListNode current = first;

            do
            {
                if (current.data == null)
                {
                    if (element == null)
                    {
                        results.insertLast(current);
                    }
                }
                else
                {
                    if (current.data.equals(element))
                        results.insertLast(current);
                }

            }
            while ((current = current.next) != null);

            @SuppressWarnings("unchecked")
            ListNode[] resultingNodes = new LinkedList.ListNode[results.numberOfElements()];
            int i = 0;
            for (LinkedList<ListNode>.ListNode node : results.getNodes())
                resultingNodes[i++] = node.data;

            return new SearchResult(resultingNodes);
        }
    }

    public class SearchResult
    {
        public Element[] results;

        @SuppressWarnings("unchecked")
        SearchResult()
        {
            this.results = new LinkedList.Element[0];
        }

        SearchResult(Element[] results)
        {
            this.results = results;
        }

        public void remove()
        {
            for (Element e : results)
                e.remove();
        }

        public Element get(int index)
        {
            if (index < 0)
                index = results.length - 1 - index;

            if (index < 0 || index >= results.length)
                throw new IndexOutOfBoundsException("Using an invalid index");

            return results[index];
        }

        public boolean isEmpty()
        {
            return results.length == 0;
        }

        public int getSize()
        {
            return results.length;
        }

        @SuppressWarnings({ "unchecked", "rawtypes" })
        public SearchResult(LinkedList.ListNode[] nodes)
        {
            results = new LinkedList.Element[nodes.length];
            for (int i = 0; i < nodes.length; i++)
            {
                results[i] = nodes[i].getElement();
            }
        }
    }

    public class Element
    {
        public final T data;
        private ListNode node;

        Element(ListNode node)
        {
            data = node.data;
            this.node = node;
        }

        public void remove()
        {
            node.remove();
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
    }
}