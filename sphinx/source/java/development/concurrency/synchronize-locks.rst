.. _java-development-concurrency-synchronize-label:

Synchronize
=====================

Critical Section
----------------
- is a group of instructions that should be executed concurrently only by specific nr of threads and performing specific operations:
    - example: one thread should write, but multiple threads can read

Mutual exclusion
----------------
- is a property of concurrency control which should prevent a race condition by not allowing any thread entering critical section


Atomic operation
----------------
- in computer science, atomic operation is an operation that is performed wiht single prcessing cycle
- is an interrupted operation
- example:
    - increment operation steps (like i++) :
        - read value of i
        - add 1 to it
        - store result of calculation in the memory
        - assign the value to a variable
- atomic operations:
    - read / write operation
    - assigning value to a variable
    - reading value from array
    - assigning value to index in array
    - operations with types from java.util.concurrent.atomic package
    - etc

Atomic variables
----------------
    - An atomic operation is one that, for all intents and purposes , appears to happen all at once
    - It can be used AtomicInteger, AtomicLong, AtomicBoolean and AtomicReference


    .. code-block:: python
       :linenos:

       public class Counter{
         private AtomicInteger count = new AtomicInteger ;
          public void increment(){
             count.getAndIncrement(); // a single line is not atomic
          }
          public int getVlalue(){
             return count.intValue();
          } 
       }


    - In reality, even a method such a getAndIncrement() still takes several steps to execute.
      The reason this implementation is not thread-safe is something called CAS ( Compare and Swap ). What is happening is:

        - the value in count is copied to a temporary variable
        - the temporary variable is incremented
        - compare the value currently in count with the original value. If it is unchanged, then swap the old value for the new value;
    - Step 3 happens atomically. If step 3 finds that some other thread has already modified the value of count,
      then repeat steps 1-3 until we increment the field without interference

Monitor
-------
- is a mechanism to synchronize threads access to critial sections
- usually the implementation is faster, lightweigth
- usually it is provided by a framework library itself and not requesting the operating system
- consist of mutex, lock abject and condition variables
    - condition variable: is a container of threads that are waiting for a certain condition
- provide a mechanism for threads to temporarily give up exclusive access in order to wait for some condition fo be met before regaining exclusive access and resuimg their task



Synchornization
---------------
- the synchronization works with locks
- every object in Java has a built-in lock
- only methods and blocks can be synchronized, not varaibles or classes
- if a thread goes to sleep, it holds any locks it has
- synchronization on:
    - instance method will aquire the lock/monitor for the corresponding instance


        .. code-block:: python
           :linenos:

           public synchronized void doStuff(){
               System.out.println(“synchronized” );
           }
           is the same as :
           public void doStuff(){
               synchronized(this){
                   System.out.println(“synchronized” );
               }
           }


    - static method will aquire the lock/monitor for the entire class


        .. code-block:: python
           :linenos:

           public static synchronized int getCount(){
                return count;
           }
           is the same as : 
           public static int getCount(){
                synchronized(MyClass.class){
                    return count;
               }
           }

Thread interaction
------------------
- The Object class has three methods : wait(), notify(), notifyAll() that help threads communicate the status of an event that the threads care about
- All 3 methods must be called within a synchronized context, because a thread can’t invoke a wait method on an object unless it owns that object’s lock
- Every object can have a list of threads that are waiting for a signal ( a notification ) from the object.
- A thread gets in that waiting list by executing the wait() method of the target object.
- From that moment, it doesn’t execute any further instructions until the notify() method of the target object is called.
- If many threads are waiting on the same object, only one will be chosen to proceed its execution. If no threads are waiting, no action is taken.

- wait():
    - release current locks/monitor in order for other theads to use them
    - you can call this method only in a syncrhonized section (block or method) or else a RuntimeException is thrown that thread is not an ownert of a monitor
- notify():
    - makes the thread that were waiting for current lock/monitor aware that they can proceed their work
- notifyAll():
    - makes all threads waiting for current lock/monitor aware that they can proceed their work

- example: reader1 & reader2 will continue its work only after notifier call the notify() methods on the message object


    .. code-block:: python
        :linenos:

        public class Demo {

            public static void main(String[] args) throws InterruptedException {
                Message message = new Message("process it");
                Reader reader1 = new Reader(message);
                new Thread(reader1, "READER 1").start();

                Reader reader2 = new Reader(message);
                new Thread(reader2, "READER 2").start();

                Notifier notifier = new Notifier(message);
                new Thread(notifier, "NOTIFIER").start();
                System.out.println("All the threads are started");
            }
        }

        public class Reader implements Runnable {

            private Message message;

            public Reader(Message m) {
                this.message = m;
            }

            @Override
            public void run() {
                String name = Thread.currentThread().getName();
                synchronized (message) {
                    try {
                        System.out.println(name + 
                                " waiting to get notified at time:" 
                                + System.currentTimeMillis());
                        message.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(name + " waiter thread got notified at time:" + System.currentTimeMillis());
                    System.out.println(name + " processed: " + message.getMessage());
                }
            }

        }

        public class Notifier implements Runnable {

            private Message message;

            public Notifier(Message m) {
                this.message = m;
            }

            @Override
            public void run() {
                String name = Thread.currentThread().getName();
                System.out.println(name + " started");
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                    synchronized (message) {
                        message.setMessage(name + " Notifier work done");
        //              message.notify();
                        message.notifyAll();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Notifier finished work");
            }

        }


Threads interaction with synchronize
------------------------------------
- If a thread tries to enter a synchronized method and the lock is already taken, the thread is said to be blocked on the object’s lock
    - threads calling non-static synchronized methods in the same class will only block each other if they are invoked using the same instance
    - threads calling static synchronized methods will always block each other as they lock the same class
    - a static synchronized method and a non-static synchronized method will never block each other




:ref:`Go Back <java-development-concurrency-label>`.