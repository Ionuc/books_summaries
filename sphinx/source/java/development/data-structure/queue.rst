.. _java-development-data-structure-queue:

Queue Data Structures
=====================

    - a Queue is designed to hold a list of “to-dos”, or things to be processed in some way.
    - Queues are order by FIFO
        - adding elements:
            - offer(E element) -> returns false if the queue is full
            - add(E exception) -> thrown exception if the queue is full
        - Removing element:
            - remove() -> throws Exception when empty
            - pool() -> returns null when empty
        - Read wihtout removing()
            - element() -> throws Exception when empty
            - peek() -> returns null for last element

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

    - PriorityQueue:
        - is a Queue where the order of elements come out is defined by priority
        - priority just defines ordering
        - elements are ordered either by natural ordering, or according to a Comparator.

    - Stacks:
        - are LIFO : Last In, First Out
        - java.utils.Stack is deprecated as all its methods are synchronized, while working with stack normally they don't care about concurrency
    - Double Ended Queues
        - has 2 ends
        - you can add and remove from both ends (from head or tail)
        - it can be used as a Queue (add to head, remove from tail) or Stack (add to head, remove from head)
        - it extends Queue interface, so all it has all methods from Queue
        - it adds methods to work with first and last (offerFirst(), offerLast(), addFirst(), addLast(), ...)
        - provides also push() & pop() methods to work as a Stack (you don't have to care which end you pick if you use it as a Stack)


:ref:`Go Back <java-development-data-structures-label>`.