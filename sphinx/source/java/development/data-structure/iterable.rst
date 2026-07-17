.. _java-development-data-structure-iterable-label:

Iterable & Iterator
===================
Iterable
--------
- is the interface providing only one method returning the instante of Iterator:
    - iterator(): Iterator

Iterator
--------
- is the interface used to access the elements in a container and navigate in collection
- for example, ArrayList can navigate by index while LinkedList can navigate using nodes
- methods:
    - abstract: 
        - hasNext(): boolean
            - return true in case the are elements in iterator
        - next(): T
            - return next element
            - throw NoSuchElementException in case iterator has no more elements
            - throw ConcurrentModificationException in case the originl list is modified (elements removed ar added) while iterating it


    .. code-block:: python
           :linenos:

            List<integer> integers = new ArrayList(List.of(1,2,3,4,5));

            Iterator<Integer> iterator = integers.iterator();
            iterator.remove() // => will throw NoSuchElementException because next() method was not called
           
            while(iterator.hasNext()) {
                integers.remove(0);
                int nextElement = iterator.next() // => will thrown ConcurrentModificationException because of previous line
                System.out.println(nextElement);
            }


    - default:
        - remove():
            - removes the last element returned by next method
            - can throw IllegalStateException if next() method was not called , remove() method was called twice for the same next() call
        - forEachRemaining(Consumer)
            - iterate remaining elements and apply the consumer on each of them

- Fail-fast iterator:
    - is the iterator which will throw ConcurrentModificationException when it detects a modification was done in original collection

- Fail-safe iterator
    - can be used in concurrent environment
    - makes caopy of the internal data structure and iterates over the copied data structure
    - any structural modification done to the iterator doesn't affect the copied data structure
    - this iterator creates a snapshot and iterate over that snapshot
    - drawback:
        - you may not received the last changes on the list (new elements added or removed)


    .. code-block:: python
           :linenos:

        System.out.println("========== Iterator - Fail-safe iterator demo ==========");
        List<Integer> threadSafeList = new CopyOnWriteArrayList<>(integers);
        iterator = threadSafeList.iterator();
        threadSafeList.add(100);
        while (iterator.hasNext()) {
            threadSafeList.remove(0);
            int nextElement = iterator.next();
            System.out.println(nextElement); // element 100 will not be printed with this line
        }
        System.out.println(threadSafeList); // => will print [100] as this element was added after the iterator was created


- Weakly consistent iterator
    - this type of iterator can reflec some, but not necessarly all of the changes that have been made to the collection since iterator is created


    .. code-block:: python
           :linenos:

        System.out.println("========== Iterator - Weakly consistent iterator demo ==========");
        Collection<Integer> deque = new ConcurrentLinkedDeque<>(integers);
        iterator = deque.iterator();
        deque.add(100);
        while (iterator.hasNext()) {
            int nextElement = iterator.next();
            System.out.println(nextElement); // element 100 will be printed with this line
        }
        System.out.println(deque);


ListIterator
------------
- extends Iterator<E>
- can iterate the collection in reverse direction
- new methods:
    - hasPrevious()
        - return true if the iterator has more elements when traversing in reverse direction
    - previous()
        - return the reference to the previous element
        - throw NoSuchElementException in case no elements are in iterator
    - nexIndex():
        - get the index to the next element
    - previousIndex():
        - get the index to the previous element
    - set(E):
        - replace the last element that was returned 
        - it replaces elements that was returned after calling next() or previous() method
        - throw IllegalStateException in case next() or previous() was not called
    - add(E):
        - add element to the container

:ref:`Go Back <java-development-data-structures-label>`.