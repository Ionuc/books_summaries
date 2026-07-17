.. _java-development-data-structure-queue:

Queue Data Structures
=====================

Queue
-----
- is designed to hold a list of “to-dos”, or things to be processed in some way.
- elements are order by FIFO
- elements are added only on one side of the queue and are processed by the other side of the queue
    - adding elements:
        - offer(E element) -> returns false if the queue is full
        - add(E element) -> thrown exception if the queue is full
    - Removing element:
        - remove():
            - retrieves and removes the head of the queue
            - throws NoSuchElementException when empty
        - pool():
            - retrieves and removes the head of the queue
            - returns null when empty
    - Read without removing()
        - element():
            - retrives but doesn't remove the head of the queue
            - throws Exception when empty
        - peek():
            - retrives but doesn't remove the head of the queue
            - returns null for last element

    .. code-block:: python
       :linenos:

       Queue<Enquire> enquires = new ArrayDeque<>();
       Enquire enquire;
       while((enquire = enquires.poll()) != null ){
           // do something with enquire
       }

    .. code-block:: python
       :linenos:

       Queue<Enquire> enquires = new ArrayDeque<>();
       while(!enquires.isEmpty())
           Enquire enquire = enquires.remove();
           // do something with enquire
       }

Stacks
------
- are LIFO : Last In, First Out
- java.utils.Stack is deprecated as all its methods are synchronized, while working with stack normally they don't care about concurrency

Deque (Double Ended Queues)
---------------------------
- has 2 ends
- you can add and remove from both ends (from head or tail)
- it can be used as a Queue (add to head, remove from tail) or Stack (add to head, remove from head)
- it extends Queue interface, so all it has all methods from Queue
- it adds methods to work with first and last (offerFirst(), offerLast(), addFirst(), addLast(), ...)
- provides also push() & pop() methods to work as a Stack (you don't have to care which end you pick if you use it as a Stack)

- new methods:
    - a lot of duplicate methods for "first" and "last", like:
        - pollFirst(), poolLast()
        - addFirst(), addLast()
    - getFirst()
        - retrieves, but not removes the first element of the Queue
        - throw NoSuchElementException in case queue is empty
    - getLast()
        - retrieves, but not removes the last element of the Queue
        - throw NoSuchElementException in case queue is empty
    - removeFirstOccurence(Object)
        - remove first when traversing from HEAD to TAIL
        - will be closer to the HEAD of the queue
    - removeLastOccurence(Object)
        - remove the first when traversin from TAIL to HEAD
        - is closer to the TAIL of the queue
    - addAll(Collection<T>)
        - all elements are inserted into the end of the queue
    - push()
        - add elements to the HEAD of the queue
        - throw IllegalStateException in case capacity is reached
        - is simillar to addFirst(), but it is needed for compabilitiy reason with legacy Stack (with push() & pop())
    - pop():
        - is equivalent with removeFirst() method
    - descendingIterator():
        - return iterator object taht can iterate over the elements in revers sequential order, from the last element(tail) to the first element(HEAD)


PriorityQueue
-------------
- is a Queue where the order of elements come out is defined by priority
- priority just defines ordering
- elements are ordered either by natural ordering, or according to a Comparator.


Popular Imeplementation
-----------------------
- AbstractQueue:
    - doesn't allow null elements
- ArrayBlockingQueue:
    - a fixed size FIFO blocking queue based on an array
    - is thread-sae
- ArrayDeque:
    - resizable array implementation of the Deque
- ConcurrentLinkedDeque:
    - an unbounded concurrent dequeue based on linked nodes
- ConcurrentLinkedQueue:
    - an unbounded concurrent queue based on linked nodes 
- DelayQueue:
    - can only contain element which implements the Delay interface - elements that become active after a certain time
    - will only deliver elements whose delays have expired
- LinkedBlockingQueue:
    - obtionally bounded FIFO blocking queue backed by linked nodes
- LinkedBlockingDequeue:
    - the concurrent implementation of Deque
    - is thread-safe
- LinkedList:
    - doubly-linked list implementation of the List and Deque
    - permits null elements
- LinkedTransferQueue:
    - an unbounded TransferQueue based on linked nodes
- PriorityBlockingQueue:
    - an unbound blocking priority queue baked by a heap
    - is thread-safe
- PriorityQueue:
    - orders its element accorting to their natural order or a Comparator
    - it doesn't work using FIFO principal, but rather returning the element with the highets priority(using the Comparator)
    - in case elements are added to the queue, but elements are not extending Comparable or the queue was not created using a Comparator, then ClassCastException is thrown because the queue needs to know how to compare the elements
- SynchronousQueue:
    - a blocking queue where each insert operation must wait for a correponding remove operation by another thread



:ref:`Go Back <java-development-data-structures-label>`.