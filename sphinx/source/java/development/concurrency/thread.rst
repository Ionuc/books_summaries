.. _java-development-concurrency-thread-label:

Thread
=======

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


Instantiating a Thread
----------------------

    .. code-block:: python
       :linenos:

        public class FirstMultithreadingProgram extends Thread {

            public static void main(String[] args) {
                
                Runnable task = new DefaultRunnable();
                Thread t1 = new Thread(task);
                
                Thread t2 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("I'm a new thread! My name is " + Thread.currentThread().getName());
                    }
                });
                
                Thread t3 = new FirstMultithreadingProgram();
                
                Thread t4 = new Thread(() -> System.out.println("I'm a new thread! My name is " 
                        + Thread.currentThread().getName()));
                
                Thread t5 = new Thread(FirstMultithreadingProgram::execute);
            }
            
            public static void execute() {
                System.out.println("I'm a new thread! My name is " + Thread.currentThread().getName());
            }

            @Override
            public void run() {
                System.out.println("I'm a new thread! My name is " + Thread.currentThread().getName());
            }
            
        }


        class DefaultRunnable implements Runnable {

            private String externalString;
            
            public DefaultRunnable() {
            }
            
            public DefaultRunnable(String externalString) {
                this.externalString = externalString;
            }
            
            @Override
            public void run() {
                System.out.println("I'm a new thread! My name is " + Thread.currentThread().getName());
                // use fields of class if needed
            }
            
        }


Starting a thread
-----------------
- A thread is starting when its start() method is called. After the call of start() method :
    - a new thread of execution starts ( with a new call stack )
    - the thread moves from new state -> runnable state
    - when thread gets a chance to execute, its target run() method will run Calling run() method it doesn’t means that it will be executed on a different thread.

Thread properties
-----------------
- name: String
- priority: int
    - is a value between 1-10 that will be used by thread shceduler to decide what thread is more important to worl with resources next
- daemon: boolean
    - default to false
- interrupted: boolean
    - is sued to indicate whether thread is interrupted and we need to stop its execution or to proceed
- target: Runnable
    - the instruction which this thread should do
- group: ThreadGroup:
    - is used to group threads together in one group
    - every thread, except the original thread group has a parent
    - the name of the original thread is "main"

- properties used by JVM:
    - stillborn: boolean
    - eetop: long

Threads instance methods
------------------------
    - start():
        - instace method
        - start the thread
    - run():
        - instance method
        - defines what the thread does
        - calling this method will not create a new thread of execution, it will act as a normal method call
    - join():
        - static method
        - if you have a thread B that can’t do its work until another thread has completed its work, then you want thread B to “join” thread A
        - this mean thread B will not become runnable until A has finished
        - it throws InterruptedException in case other thread interupt this thread while being sleeping

    .. code-block:: python
       :linenos:

        public static void main(String[] args) throws InterruptedException {
            InterruptDemo runnableTask = new InterruptDemo();
            Thread thread = new Thread(runnableTask);
            thread.start();

            Thread.sleep(2000);

            System.out.println("in main() - interrupting other thread");
            thread.interrupt();
            
            thread.join(); // will make the main method to sleep and to be be runnable state after "thread" finished its work
            System.out.println("in main() - leaving");
        }


    - interrupt()
        - used to interrupt the execution of this thread
        - change interruption flag inside the thred object
    - isInterrupted():
        - return true or false if the thread is interrupted

Thread static methods
---------------------
- Thread.sleep()
    - will make current thread to sleep for the amount of time and after that to move in Runnable state
    - it throws InterruptedException when other thread will interrupt this current thread
- Thread.currentThread()
    - return the current thread which is executing
- Thread.interrupted()
    - return booleam value and it returns state of interrupteion flag, but it also clear it and return it back to false
- Thread.yield():
    - makes the currently running thread head back to runnable to allow other threads of the same priority to get their turn
    - the thread scheduler can ignore this call and continue processing this thread

    .. code-block:: python
       :linenos:


        public class YieldDemo {

            public static void main(String[] args) {
                var t0 = new Thread(() -> {
                    Thread.yield();
                    System.out.println(Thread.currentThread().getName());
                });

                var t1 = new Thread(() -> System.out.println(Thread.currentThread().getName()));
            
                t0.start();
                t1.start();
                // running multiple times, the order ot thread execution is not the same, t0 will not be executed the last all time
            }

        }


Deamon Threads
--------------
- JVM is stopped when there are only daemin threads running
- exist as long as some non-daemon threads exist
- may evaporate if non-deamon threads left
    - no clean up
    - no finalizers called
- used for support threads : such as GC

- example of running thread in non-deamon: the process will run forever and printing the line from t1 because t1 is not daemon

    .. code-block:: python
       :linenos:

        public class DaemonThreadDemo {
            
            public static void main(String[] args) throws InterruptedException {
                
                var t1 = new Thread(() -> {
                    while (true) {
                        try {
                            TimeUnit.MILLISECONDS.sleep(500);
                            System.out.println("Daemon is working");
                        } catch (InterruptedException e) {
                            
                        }
                    
                    }
                });
                
        //      t1.setDaemon(true);
                t1.start();
                
                TimeUnit.SECONDS.sleep(2);
                System.out.println("Main thread finished");
                
            }
        }

- example of running thread in deamon: the process will be closed after main thread printing the last line because t1 is daemon

    .. code-block:: python
       :linenos:

        public class DaemonThreadDemo {
            
            public static void main(String[] args) throws InterruptedException {
                
                var t1 = new Thread(() -> {
                    while (true) {
                        try {
                            TimeUnit.MILLISECONDS.sleep(500);
                            System.out.println("Daemon is working");
                        } catch (InterruptedException e) {
                            
                        }
                    
                    }
                });
                
                t1.setDaemon(true);
                t1.start();
                
                TimeUnit.SECONDS.sleep(2);
                System.out.println("Main thread finished");
                
            }
        }


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
- a thread is allowed to access information about its own thread group, but not to access information regarding other threads

- example: 
    - create a thread group
    - create new threads to print a message and to exit in case of InterruptedException
    - interrupt thread group => should exist all threads

    .. code-block:: python
       :linenos:

        public class ThreadGroupDemo {

            public static void main(String[] args) throws InterruptedException {
                
                ThreadGroup threadGorup = new ThreadGroup("group");
                
                var t0 = new Thread(threadGorup, ThreadGroupDemo::execute);
                var t1 = new Thread(threadGorup, ThreadGroupDemo::execute);
                var t2 = new Thread(threadGorup, ThreadGroupDemo::execute);
                var t3 = new Thread(threadGorup, ThreadGroupDemo::execute);
                var t4 = new Thread(threadGorup, ThreadGroupDemo::execute);

                t0.start();
                t1.start();
                t2.start();
                t3.start();
                t4.start();
                
                TimeUnit.SECONDS.sleep(2);
                
                threadGorup.interrupt();
            }
            
            public static void execute() {
                while (true) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(500);
                        System.out.println(Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        break;
                    }
                    
                }
            } 
        }

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

Thread interaction
------------------
    - The Object class has three methods : wait(), notify(), notifyAll() that help threads communicate the status of an event that the threads care about
    - All 3 methods must be called within a synchronized context, because a thread can’t invoke a wait method on an object unless it owns that object’s lock
    - Every object can have a list of threads that are waiting for a signal ( a notification ) from the object.
      A thread gets in that waiting list by executing the wait() method of the target object.
      From that moment, it doesn’t execute any further instructions until the notify() method of the target object is called.
      If many threads are waiting on the same object, only one will be chosen to proceed its execution. If no threads are waiting, no action is taken.

:ref:`Go Back <java-development-concurrency-label>`.