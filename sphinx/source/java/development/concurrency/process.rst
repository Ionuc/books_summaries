.. _java-development-concurrency-threads-label:

Threads
=======

What is a process
-----------------
- process is an instance of a computer program,
- a computer progam is just a set of instructions for a machine
- a process is the execution of these istructions
- a process has a self-contained execution environment:
    - has its own memory space
- most Operating systems support inter-process communication resources used to interract between different processes:
    - pipes
    - sockets

What is a thread of execution
-----------------------------
- each process may be executed in one or more threads of execution:
    - some instructions are executed in parallel
- in computer sciente, concurrency is the ability of different parts or units of a program, algorithm or problem to e executed out of order at the same time simultaneously without affecting the final result
- so, a process may be executed in multiple threads
- all threads shares processors resources

What is a thread
-----------------
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


    .. image:: ../../../images/java/development/concurrency/processors-memory.png
        :align: center

    - L1 and L2 caches are local to each physical core, whereas the L3 caches is shared between all cores

Cache lines
-----------
    - often access contiguous memory
    - word at a time transfer is slow
    - so transfer blocks of data in one go
    - the caches can hold these blocks in cache lines


:ref:`Go Back <java-development-concurrency-label>`.