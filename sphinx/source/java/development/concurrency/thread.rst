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


Java 21 improvements
--------------------
- new implemention of Virtual Threads which extends Threads:
    - Thread.startVirtualThread(Runnable)
        - will start and run a virtual thread


    .. code-block:: python
           :linenos:

            Thread vThread = Thread.startVirtualThread(() -> {
                System.out.println("Hello from a virtual thread (Thread.startVirtualThread)");
            });



:ref:`Go Back <java-development-concurrency-label>`.