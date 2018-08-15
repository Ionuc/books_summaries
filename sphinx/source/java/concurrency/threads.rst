.. _threads-label:

Threads
=======
What is exactly a thread ? In java, “thread” means two different things :
    - An instance of class java.lang.Thread -> is just an object with variables & methods and lives and dies on the heap
    - A thread of execution -> is an individual process that has its own call stack

Multitasking vs non-multitasking
--------------------------------
    - multitasking
        - running several processes on the same machine
        - each could make progress without any user help
    - non-multitasking
        - only one process can be run
        - only the active process can make progress

Process Characteristics
-----------------------
    - usually standalone
    - ofteh no knowledge of each other
    - own memory allocation and usually use segmentation
    - segmentation:
        - prevents a badly behaved or malicious program accessing or modifying data in other processes
        - any communication between related processes has to take place by
            - the file system
            - sockets
            - specifically designed shared memory
    - each process is likely to have a fair amount of associated accounting data, detailing the process ID, the user ID that ran it,
      state and so on

    - each process has own memory allocation

Time Slicing
------------
    - each task is allocating a certain amount of time, a time slice on the CPU to execute
    - when its time was up, an interrupt occured, prompting the operating system to save the task's state
    - another's is restored and continued

Multithreading
--------------
    - is ability to run several threads of computation at the same time in the same process
    - threads share memory and resources
    - threads likely to have lower overgeads than processes
    - threads are part of the same program
    - cannot exist outside the process's lifetime
    - does not require the ability to run in parallel:
        - time slicing allows a scheduler to choose a thread to run on a free core
        - there is no guarantee that threads will ever run in parallel
        - we could even be running on the system with just a single processor core

Will Threads Run in Parallel ?
------------------------------
    - no guarantee
    - the scheduler uses time slicing to give each an allocation of time on the available cores
    - is managed by the JVM's scheduler

Threads Are Lightweight, but Not Free
-------------------------------------
    - need their own stack
    - have some memory overhead
    - creating and destroying takes time
    - scheduler will aslo need to ensure the thread's state is saved before another can execute
    - swapping between threads takes time

Concurrency
-----------
    - when a task is broken down into smaler pieces which we run simultaneously, we achieve concurrency
    - can be carried out in different ways:
        - via processes in multithreading
        - threads in multitasking
        - distribution of work across a grid or the clound
    - benefits:
        - allows problem to be solved far quiker: in particular, very large scientific and finacial problems

Parallelism
-----------
    - multithreading and multitasking do not required it
    - same neither does concurrency

Uses of Concurrency
-------------------
    - keep GUI responsive
    - Actors which interact which application

Memory Accesses are slow
------------------------
    - memory accesses have a small, but not insignificant physical distance to cover between the processor and memory via a bus
    - getting exclusive access to that bus may take many clock cycles
    - because of this there is a pool of memory inside the processor where commonly used data can reside
    - this memory is called cache
    - after filling the cache, we can read and write to that data without having to wait for it to be transferred to and from the memory
    - in modern processros there can be 3 levels of caches, we will term these caches L1, L2, L3. The higher the numberm the larger, but
      slower the cache is


    .. image:: ../../images/java/concurrency/processors-memory.png
        :align: center

    - L1 and L2 caches are local to each physical core, whereas the L3 caches is shared between all cores

Cache lines
-----------
    - often access contiguous memory
    - word at a time transfer is slow
    - so transfer blocks of data in one go
    - the caches can hold these blocks in cache lines

Instantiating a Thread
----------------------
    - For case of extending the Thread

    .. code-block:: python
       :linenos:

       MyThread thread = new MyThread();

    - For case of implementing Runnable

    .. code-block:: python
       :linenos:

       MyRunnable r = new MyRunnable(); -> runnable is the “job”
       Thread t = new Thread(r); -> thread is the “worker”

Starting a thread
-----------------
A thread is starting when its start() method is called. After the call of start() method :
    - a new thread of execution starts ( with a new call stack )
    - the thread moves from new state -> runnable state
    - when thread gets a chance to execute, its target run() method will run
      Calling run() method it doesn’t means that it will be executed on a different thread.
	  
Thread states
-------------
Thread States:
    - new state - > when it was created and instantiated, but start() method was not called
    - runnable -> when the start() method is called ( even if the run() method may not have been  called ).
      The thread is eligible to run but the scheduler has not selected it to be the running thread. 
      A thread enter this states when:

        - start() method was called
        - after either running or coming back from a blocked, waiting or sleeping state
    - running -> is the state when the thread was selected by scheduler to be the running thread
    - waiting / blocked / sleeping -> it is the state when a thread is not eligible for running. 
      in this state, the thread is still alive
      a thread may be blocked waiting for a resource 
      a thread may be sleeping because the thread’s run code tells it to sleep
      a thread may be in waiting for another thread to wake it up
    - dead -> after run() method completes
      once a thread is dead, it can never be brought back to life
      if you call start() method on a dead thread -> RunTime exception

Threads methods
---------------
    - start():
        - instace method
        - start the thread
    - run():
        - instance method
        - defines what the thread does
        - calling this method will not create a new thread of execution, it will act as a normal method call
    - yield():
        - static method
        - makes the currently running thread head back to runnable to allow other threads of the same priority to get their turn
    - join():
        - static method
        - if you have a thread B that can’t do its work until another thread has completed its work, then you want thread B to “join” thread A
        - this mean thread B will not become runnable until A has finished

Deamon Threads
--------------
    - exist as long as some non-daemon threads exist
    - may evaporate if non-deamon threads left
        - no clean up
        - no finalizers called
    - used for support threads : such as GC

Handling Exception from Threads
-------------------------------
    - could catch before exiting and save
    - the Thread has method :uncaughtExceptionHandler() which has an parameter of type UncaughtExceptionHandler:
        - if we need to handle checked exception via the uncaughtExceptionHandler, we need to wrap them in an unchecked exception before rethrowing
    - when a thread throws an uncaught uncheked exception, the private method dispatchUncaughtException is called by JVM
    - this method used uncaughtExceptionHandler to determin what to call
    - by default when this has not been set, the handler in ThreadGroup is called
    - this checks whether a defaultUncaughtExceptionHandler has been set via the static method, and if not, it prints a stack trace

ThreadGroup
------------
    - was originally intended so different groups of threads could be handle together. For example:
        - to have their thread priorities adjust together
        - to be interrupted together
    - deprecated because of thread pools

ThreadLocal
-----------
    - want to avoid global variables:
        - cause additional coupling
        - modifications can cause problems
    - but useful:
        - to not have to pass values for formatters and loggers
        - for threads to have their own copy accessed via same global reference
    - to use ThreadLocal we simply create an instance of a ThreadLocal per a variable we wish to use in this way
    - we use to set method, set a value, get to get the value back and remove to clear it out
    - if 2 threads manipulate the same variable, they can use the same ThreadLocal instance safely since the value set and returned will be
      specific to the thread that it set it

    - beaware:
        - data persists untul thread dies or no instances of ThreadLocal left
        - danger with long-lived threads to not have memory leaks:
            - if task finishes/dies without lcearing out Threadlocal objects, the thread might still exist and a Threadlocal may stil exist elswhere
            - lost knowledge that the thread stored an object
            - one way ot be safe is by capturing all exception and by calling Remove on any ThreadLocal we maye have stored data in

Thread Schedular
----------------
    - Thread Scheduler is the part of JVM that decides which thread should run at any given moment and also takes threads out of the run state.
    - only threads which are in runnable state can be selected to be the next thread which will be executed.
    - Threads  always run with some priority, usually represented as a number between 1 - 10.
      The scheduler in most JMVs uses preemptive, priority-based scheduling:

        - if a thread enters the runnable state and has a higher priority than any of the threads in the pool and a higher priority than
          the current running thread, the lower-priority thread will be bumped back to runnable and the higher-priority thread will be chosen to run.
    - Don’t rely on thread priorities when designing your multithreaded application because thread-scheduling priority behavior is not guaranteed.
    - What is also not guaranteed is the behavior when thread in the pool are having the same priority.

Thread interaction
------------------
    - The Object class has three methods : wait(), notify(), notifyAll() that help threads communicate the status of an event that the threads care about
    - All 3 methods must be called within a synchronized context, because a thread can’t invoke a wait method on an object unless it owns that object’s lock
    - Every object can have a list of threads that are waiting for a signal ( a notification ) from the object.
      A thread gets in that waiting list by executing the wait() method of the target object.
      From that moment, it doesn’t execute any further instructions until the notify() method of the target object is called.
      If many threads are waiting on the same object, only one will be chosen to proceed its execution. If no threads are waiting, no action is taken.

:ref:`Go Back <java-concurrency-label>`.