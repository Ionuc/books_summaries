.. _thread-problems:

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
    - prevent deadlocks:
        - take the mutexes in the same order
        - replace two or more mutexes with a single one
        - use try-lock
		
Livelock
--------
    - is similar to deadlock, except that the states of the processes involved constantly change with regard to one another,
      none progressing

    - threads aren't permanently blocked as in deadlock, so can:
        - back off and retry
        - wait for a bit
        - do some other work
        - try to resolve the situation
    - still can't take all mutexes

Starvation
----------
    - when threads are not getting enought execution time to carry out their tasks
    - possible starvations:
        - reader/writer problem
        - due to Scheduler
        - due to lack of resources

:ref:`Go Back <java-concurrency-label>`.