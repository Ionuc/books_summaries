.. _pessimistic_optimistic_locking-label:


Locking strategies
==================
    - there are 2 locking strategies which can be used:
        - pessimistic locking
        - optimistic locking

Pessimistic Locking
-------------------
    - the premise used is that conflicts or inconsistencies are probable when multiple processes access the same resource simultaneously
    - the main idea behind pessimistic locking is to acquire locks on resources preemptively, maintaining exclusive access to these resources for the entire duration of a transaction or critical operation
    - it prevents other users or processes from accessing or modifying the locked resource until the lock is released
    - Key concepts:
        - Acquiring Locks Upfront: 
            - before performing any read or write operation on a resource, pessimistic locking acquires locks on the resource
        - Exclusive Access: 
            - Once a lock is obtained, it grants exclusive access to the locked resource to the transaction that holds the lock
            - Other transactions must wait until the lock is released before they can access the resource
        - Potential for Waiting: there might be waiting times for other transactions if they request access to the locked resource
        - Different Granularities: 
            - Pessimistic locking can operate at various granularities, such as database-level locks, table-level locks, row-level locks, or even finer-grained locks at the column level

    - Pros:
        - Ensures data consistency by preventing concurrent modifications that could lead to inconsistencies
        - Straightforward approach to managing concurrent access
    - Cons:
        - Potential for increased contention and waiting times, especially in scenarios with high concurrency
        - Locks held for extended periods might impact system performance and throughput.



.. code-block:: python
    :linenos:

    import java.util.concurrent.locks.ReentrantLock;

    public class CounterWithLock {
        private static int counter = 0;
        private static final ReentrantLock lock = new ReentrantLock();

        public static void main(String[] args) throws InterruptedException {
            Thread[] threads = new Thread[100000];
            for (int i = 0; i < 100000; i++) {
                threads[i] = new Thread(() -> {
                    lock.lock();
                    try {
                        counter++;
                    } finally {
                        lock.unlock();
                    }
                });        
                threads[i].start();
            }
            for (Thread thread : threads) {
                thread.join();
            }
            System.out.println("Counter with lock (pessimistic locking): " + counter);
        }
    }




- when to use Pessimistic Locking:
    - High Contention: in scenario when conflicts are frequent or expected, such as banking system handling critical transactions
    - Long Transactions: When transactions hold locks for extended periods due to complex operations, pessimistic locking can prevent inconsistencies by keeping resourced locked until the transaction completes



Optimistic Locking
------------------
    - is a concurrency control mechanism used in computer systems, particularly in databases, to manage concurrent access to shared resources with an optimistic assumption that conflicts are infrequent or less likely to occur
    - key concepts:
        - Validation Before Commit:
            - Rather than preventing concurrent access, optimistic locking defers conflict detection until the time of committing changes
            - It allows transactions to proceed independently and optimistically assumes that conflicts won’t arise during the execution phase
        - Validation at Commit Time:
            - Before committing changes, the system checks whether any other transaction has modified the resource since the current transaction started
            - This validation is typically done by comparing versions or timestamps associated with the resource
        - Handling Conflicts:
            - If conflicts are detected during the validation phase, the system takes appropriate action, like rolling back the current transaction or retrying it to incorporate the latest changes

    - Pros:
        - Reduced contention: Allows concurrent access without blocking, potentially improving system concurrency
        - Minimal locking overhead: Transactions proceed independently, reducing the need for acquiring and managing locks
    - Cons:
        - Increased chances of conflicts: Might require retrying transactions or handling conflicts, leading to additional logic and complexity
        - In scenarios with frequent conflicts, optimistic locking might not be the most efficient approach


.. code-block:: python
    :linenos:

    import java.util.concurrent.atomic.AtomicInteger;

    public class OptimisticLockingExample {
        private static AtomicInteger counter = new AtomicInteger(0);
        private static int version = 0;

        public static void main(String[] args) throws InterruptedException {
            int numThreads = 100000;
            Thread[] threads = new Thread[numThreads];

            for (int i = 0; i < numThreads; i++) {
                threads[i] = new Thread(() -> {
                    int localCounter;
                    
                    do {
                        localCounter = counter.get(); // Get the current counter value

                    // Compare-and-swap operation
                    } while (!counter.compareAndSet(localCounter, localCounter + 1));
                });

                threads[i].start();
            }

            for (Thread thread : threads) {
                thread.join();
            }

            System.out.println("Counter value after optimistic locking: " + counter);
        }
    }


- when to use it:
    - Low contention: In scenarios where conflicts are rare or infrequent
    - Short Transactions: When transactions are short-lived and the likelihood of conflicts is minimal

:ref:`Go Back <db-label>`.