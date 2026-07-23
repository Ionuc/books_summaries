.. _java-development-concurrency-thread-problems:

Thread problems
===============

Race Conditions
---------------
- incorrect behavior caused by threads interleaving and executing code in an unintended order despite the data
      being correctly synchronized

- presence of a potential race condition means the code is not thread-safe
- can be demonstrated with code using:
    - putting code in a loop
    - using sleeps
    - using a CountDownLatch
    - CyclicBarrier
    - Phaser

Potential impacts
-----------------
- memory leak
- inconsistent data:
    - lost update:
        - multiple threads updating in the same time the same resource
    - dirty reads:
        - reading outdate date
- deadlock
- livelock

Critical Section
----------------
- one or more parts of the code which may not be accessed by more than one thread at a time

Mutex
-----
- is short for mutual exclusion object
- others wanting access have to wait
- pros:
    - protect from race conditions
- cons:
    - may have to wait
    - incurs performance penalty

Deadlock
--------
- is where a threads cannot make progress because they need a mutex another thread involved in
- no thread carries out its critical section
- no thread acquires all the mutexes it requires
- two or more threads must be involved in a deadlock
- no way to break deadlocks
- other deadlocks:
    - one thread locking rows of a table in a database request a mutex
    - another thred has the mutex but waiting to access the locked rows

    .. code-block:: python
       :linenos:

        public class DeadlockIssueDemo {
            
            private static Object lock1 = new Object();
            private static Object lock2 = new Object();
            
            public static void main(String[] args) {
                
                new Thread(() -> {
                    synchronized (lock1) {
                        System.out.println("Lock1 is captured");
                        try {
                            TimeUnit.MILLISECONDS.sleep(500);
                        } catch (InterruptedException e) {}
                        
                        synchronized (lock2) {
                            System.out.println("This block will never be executed");
                        }
                    }
                }).start();
                
                new Thread(() -> {
                    synchronized (lock2) {
                        System.out.println("Lock2 is captured");
                        synchronized (lock1) {
                            System.out.println("This block will never be executed");
                        }
                    }
                }).start();
                
            }
        }

- prevent deadlocks:
    - take the mutexes in the same order
    - replace two or more mutexes with a single one
    - use try-lock
    - use tie constraints to aquire lock
	


Livelock
--------
- is similar to deadlock, except that the states of the processes involved constantly change with regard to one another, none progressing

- threads aren't permanently blocked as in deadlock, so can:
    - back off and retry
    - wait for a bit
    - do some other work
    - try to resolve the situation
- still can't take all mutexes
- solution:
    - thread should stop the repeating action if no progress has been detected
    - pass the state to the other thread only a limited nr of times, after that, thread should continue processing and finish execution

Starvation
----------
- when threads are not getting enought execution time to carry out their tasks
- this happends when shared resources are made unavailable for long periods of greedy threads
- possible starvations:
    - reader/writer problem
    - due to Scheduler
    - due to lack of resources


:ref:`Go Back <java-development-concurrency-label>`.