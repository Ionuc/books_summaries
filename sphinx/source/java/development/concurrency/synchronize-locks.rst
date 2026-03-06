.. _synchronize-locks:

Synchronize and Locks
=====================
    - the synchronization works with locks
    - every object in Java has a built-in lock
    - only methods and block can be synchronized, not varaibles or classes
    - if a thread goes to sleep, it holds any locks it has
    - synchronization on:
        - instance method will aquire the lock for the corresponding instance

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

        - static method will aquire the lock for the entire class

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

Threads interaction with synchronize
------------------------------------
If a thread tries to enter a synchronized method and the lock is already taken, the thread is said to be blocked on the object’s lock
    - threads calling non-static synchronized methods in the same class will only block each other if they are invoked using the same instance
    - threads calling static synchronized methods will always block each other as they lock the same class
    - a static synchronized method and a non-static synchronized method will never block each other

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

Locks
-----
    - Locks are used  for creating segments of code that require exclusive execution
    - they are used to optain a lock in one method and release it in another
    - multiple wait/notify/notifyAll pools per block - threads can select which pool (Condition) they wait on
    - provides ability to acquire a lock and take an alternative action if locking fails
    - implementations : ReetrantLock, ReetrantReadWriteLock

    .. code-block:: python
       :linenos:

        Object obj = new Object();
        synchronized(obj){ // traditional locking, blocks until acquired
		             // work
        } // release lock automatically
        is equivalent with :
        Lock lock = new ReentrantLock()
        lock.lock(); // locks until acquired
        try{
           // do work here
        } finally {
           lock.unlock;
        }

    - It is recommended that you follow the lock() method with a try-finally blocks, which release the blocks
    - One of the very powerful feature is the ability to attempt ( and fail) to acquire a lock

    .. code-block:: python
       :linenos:

        Lock lock = new ReentrantLock()
        boolean locked = lock.tryLock(); // try without locking
        if (locked){
           try{
              // do work here
           } finally {
              lock.unlock;
           }
        }

    - You can process a different resource and come back to the failed lock later.. Another benefit of the tryLock  is deadlock avoidance
    - With traditional synchronization wout must acquire the same order across all threads.
      You should not unlock a Lock if it wasn’t acquired else an IllegalMonitorStateException will be thrown

Conditions
----------
    - A Condition provides the equivalent of the traditional wait(), notify() and notifyAll()
    - The traditional wait and notify methods allow developers to implement an await/signal pattern
    - You use an await/signal pattern when you would use locking, but with the added stipulation of trying to avoid spinning ( endless checking if it is okay to do something )

ReetrantReadWriteLock
---------------------
    - A ReetrantReadWriteLoc is not actually a Lock. it implements the ReadWriteLock interface
    - It produce two specialized instances, one to a read lock and the other one to a write lock:

    .. code-block:: python
       :linenos:

        ReentrantReadWriteLock rwl = new ReetrantReadWriteLock();
        Lock readLock = rwl.readLock();
        Lock writeLock = rwl.writeLock();

    - These two locks are a matched set - one cannot be held at the same time as the other ( by different threads )
    - What makes this lock unique is that multiple threads can hold the read lock at the same time, but only one thread can hold the write lock at a time

    .. code-block:: python
       :linenos:

        public class MaxValueCoolection{
            private List<Integer> integers = new ArrayList<>();
            private ReetrantReadWriteLock rwl = new ReetranReadWriteLock();

            public void add(Integer i){
                rwl.writeLock.lock(); // one at a time
                try{
                    integers.add(i);
                } finally {
                    rwl.writeLock().unlock();
                }
            }

            public int findMax(){
                rwl.readLock.lock(); // many at once
                try{
                    return Collections.max(integers);
                } finally{
                    rwl.readLock.unlock();
                }
            }
        }

:ref:`Go Back <java-concurrency-label>`.