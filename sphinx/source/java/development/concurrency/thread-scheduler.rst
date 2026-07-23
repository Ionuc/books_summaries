.. _java-development-concurrency-thread-scheduler-label:

Thread Scheduler
================

- Thread Scheduler is the part of JVM that decides which thread should run at any given moment and also takes threads out of the run state.
- only threads which are in runnable state can be selected to be the next thread which will be executed.
- Threads always run with some priority, usually represented as a number between 1 - 10.

- decision making factors:
    - priority: 
        - threads with higher priority will be taken first
        - Don’t rely on thread priorities when designing your multithreaded application because thread-scheduling priority behavior is not guaranteed.
        - What is also not guaranteed is the behavior when thread in the pool are having the same priority.
        - during initialization, child thread take the same priority as the parent
    - arrival time:
        - the thread scheduler also will take into account the fac when thread arrived and how long each of them were waiting
        - in case with similar priority of threads, arrival time is one of the factrs that will be considered

- Scheduling algorithms
    - when threads are executed concurrently, processor allocates a small amount of time that is usually called time slice to each thread
    - the task of thread scheduler is to verify each thread and make a decision what thread will get processors
    - algorithms:
        1) preemtive-priority scheduling:
            - if a thread enters the runnable state and has a higher priority than any of the threads in the pool and a higher priority than the current running thread, the lower-priority thread will be bumped back to runnable and the higher-priority thread will be chosen to run.
            - the scheduler in most JMVs uses preemptive, priority-based scheduling
            - other threads can be executed if:
                - in case other threads has higher priority of current thread
                - current thread went to waiting state
                - current thread was interrupted
                - or with the help of yield method

    .. code-block:: python
       :linenos:

        public class PriorityDemo {

            
            public static void main(String[] args) {
                var t0 = new Thread(PriorityDemo::execute);
                var t1 = new Thread(PriorityDemo::execute);
                var t2 = new Thread(PriorityDemo::execute);
                var t3 = new Thread(PriorityDemo::execute);
                var t4 = new Thread(PriorityDemo::execute);
                var t5 = new Thread(PriorityDemo::execute);
                
                t0.setPriority(1);
                t1.setPriority(10);
                t2.setPriority(10);
                t3.setPriority(3);
                t4.setPriority(6);
                t5.setPriority(4);
                
                t0.start();
                t1.start();
                t2.start();
                t3.start();
                t4.start();
                t5.start();
                // running multiple time you will see t0 will be executed first and in some cases t1 or t2 will be executed last
            }
            
            public static void execute() {
                System.out.println(Thread.currentThread().getName());
            }
            
        }


        2) First Come Frist Server scheduling (FCFS):
            - thread scheduler assigns CPU time to the threads that appear to be the one who request it first
        3) Time-slicing scheduling:
            - each thread gets executed cyclically one after another
            - the scheduler will track whether thread is finished his execution within a time slice or no
            - in case thread finished, it will be removed from queue and thread scheduler will work with other threads only


:ref:`Go Back <java-development-concurrency-label>`.