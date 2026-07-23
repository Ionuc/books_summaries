.. _java-development-concurrency-syncrhonizers-locks-label:

Locks
=====
- Locks are used  for creating segments of code that require exclusive execution: a mechanism that enforces limits on access to resource
- usually, mutexes are provided by the operating system kernel
- usually, libraries and frameworks simply provide an interface to invoke mutex
- this makes them heavy weight slower, but alows us ot achieve the expected result to synchronize threads
- they are used to optain a lock in one method and release it in another
- multiple wait/notify/notifyAll pools per block - threads can select which pool (Condition) they wait on
- provides ability to acquire a lock and take an alternative action if locking fails

- Lock interface methods:
    - lock()
        - it acquire lock or put the thread and waiting until lock can be acquire
        - is similar with synchronized block / methods
    - unlock()
        - releases the lock acquired
    - tryLock()
        - returns true if the lock can be acquired or false, it not
    - tryLock(time, Timeunit)
        - you can try to acquire lock within the specified amount of time
        - in case the time is elapsed, you can proceed with alternative steps
        - allows to wait for a specific time and after that to proceed execution
    - lockInterruptibly():
        - is similar with lock(), but it allows the blocked thread to be interrupted and resume hte execution through a thrown of InterruptedException
    - newCondition()
        - you can declare a lock conditions that will help you to coordinate work between multiple threads
        - return a Condition object

- implementations:
    - ReetrantLock
    - ReetrantReadWriteLock
    - StampedLock

ReetrantLock
------------
- It is recommended that you follow the lock() method with a try-finally blocks, which release the blocks

.. code-block:: python
    :linenos:

    public class ReentrantLockDemo {
        private static int counter;
            
        private Lock lock = new ReentrantLock();

        public static void main(String[] args) throws InterruptedException {
            var thisInstance = new ReentrantLockDemo();
            var es = Executors.newFixedThreadPool(4);
            IntStream.range(0, 10000).forEach((i) -> es.execute(thisInstance::incrementWithLock));
            terminateExecutorService(es);
            System.out.println(counter);
        }

        public void incrementWithLock() {
            try {
                lock.lock();
                counter++;
            } finally {
                lock.unlock();
            }
        }
            
        public void increment() {
            synchronized(this) {
                counter++;
            }
        }

        private static void terminateExecutorService(ExecutorService es) throws InterruptedException {
            es.shutdown();
            es.awaitTermination(3, TimeUnit.SECONDS);
            es.shutdownNow();
        }
    }


- the lock is called reentrant if the thread that holds the lock can lock it again
- the lock is called non-reentrat lock in case the lock cannot be lock again if it is locked, not even by the tread that holds the lock
- a reetrant lock must unlock it as many times as it has locked it in order to fully unlock it for other threads

.. code-block:: python
    :linenos:

    public class ReentrantLockDemo2 {

        private Lock lock = new ReentrantLock();

        public static void main(String[] args) throws InterruptedException {
            var demo = new ReentrantLockDemo2();
            var es = Executors.newFixedThreadPool(4);
        
            es.execute(() -> System.out.println(demo.calculate("+", 2, 3)));
            es.execute(() -> System.out.println(demo.calculate("+", 7, 12)));
            es.execute(() -> System.out.println(demo.calculate("+", 87, 4)));
            es.execute(() -> System.out.println(demo.calculate("-", 2, 3)));
            es.execute(() -> System.out.println(demo.calculate("-", 57, 12)));
            es.execute(() -> System.out.println(demo.calculate("-", 110, 7)));

            terminateExecutorService(es);    
        }

        public double add(double value1, double value2) {
            try {
                lock.lock();    // LOCK is aquired 
                System.out.println("lock is acquired");
                
                return value1 + value2;
            } finally {
                lock.unlock();
                System.out.println("lock is released");
            }
        }

        public double subtract(double value1, double value2) {
            try {
                lock.lock();    // LOCK is aquired 
                System.out.println("lock is acquired");
                
                return value1 - value2;
            } finally {
                lock.unlock();
                System.out.println("lock is released");
            }
        }

        public double calculate(String operation, double operand1, double operand2) {
            try {
                lock.lock();    // LOCK is aquired 
                System.out.println("lock is acquired");

                switch (operation) {
                case "+":
                    return add(operand1, operand2);
                case "-":
                    return subtract(operand1, operand2);
                default:
                    System.out.println("Calculate works only with + and - operators and two values only");
                    return 0;
                }

            } finally {
                lock.unlock();
                System.out.println("lock is released");
            }
        }
            
        private static void terminateExecutorService(ExecutorService es) throws InterruptedException {
            es.shutdown();
            es.awaitTermination(3, TimeUnit.SECONDS);
            es.shutdownNow();
        }
    }


- One of the very powerful feature is the ability to attempt ( and fail) to acquire a lock
    - wihtout timeout:


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


    - and with a timeout:


    .. code-block:: python
       :linenos:

        public class ReentrantLockDemo3 {
            
            private static ReentrantLock lock = new ReentrantLock();
            
            public static void main(String[] args) {
                var es = Executors.newFixedThreadPool(2);
                
                es.submit(ReentrantLockDemo3::doSomethingLong);
                es.submit(ReentrantLockDemo3::doSomethingLong);
                
                es.shutdown();
            }

            private static void doSomethingLong() {
                try {
                    if (lock.tryLock(2, TimeUnit.SECONDS)) {
                        System.out.println("lock is acquired: " + lock.isLocked());
                        TimeUnit.SECONDS.sleep(3);
                    } else {
                        System.out.println("Thread didn't acquired a lock: " 
                                    + Thread.currentThread().getName());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
                
            }

        }


- you can create the ReentratLock with fair = true:
    - default value is false
    - example: 
        - you have 10 threads that are waiting to acquire a lock, some of them are waiting for a long time, some of them just arrived
        - lock doesn't guarantee the order in which threads wating to acquire the lock => can lead to thread starvation

ReetrantReadWriteLock
----------------------
- A ReetrantReadWriteLoc is not actually a Lock. it implements the ReadWriteLock interface
- It produce two specialized instances, one to a read lock and the other one to a write lock
- it is used to have better performance by separating read operation and write operation
- read lock can be acquire by multiple threads
- write lock can be acquire only by one thread


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


StampedLock
-----------
- is not reetrant, no threads can acquire multiple locks
- supports both read & write locks
- has a feature for optimistic locking for read operations
- lock acquisition methods returns a stamp that is used in other methods
- stamp is of type long
- the state of a stamped log consists of a version and mode
- 0 is returned in case lock cannot be acquired
- there are 3 modes:
    - WRITE mode:
        - when you try to acquire WRITE lock
        - write lock can be acquired with writeLock()
        - returns a stamp that can be used to unlock or convert mode
        - no read locks may be obtained
    - READ mode:
        - when you try to acquire READ lock
        - READ lock can be acquire using readLock()
        - returns a stamp which can be used to unlock or convert mode
    - Optimistic Read:
        - returns a stamp only if the lock is not currently held in write mode
- conversion methods:
    - there are 3 methods to convert to those 3 modes
    - are having as parameter the stamp




Advantages
----------
- advantages over synchornized blocks:
    - lock may be obtained and released in different places:
        - can be acquired in one method and released in another method
    - time limit to acquire lock to avoid deadlocks
        - in case the lock is not acquired, it is possible to to other activities and to come try again to aquire the lock
    - work with conditions
    - fairness for threads that are waiting for lock longer than other threads
    - allows us to interrupt thread when its waiting for the lock
    - With traditional synchronization wout must acquire the same order across all threads. You should not unlock a Lock if it wasn’t acquired else an IllegalMonitorStateException will be thrown


:ref:`Go Back <java-development-concurrency-syncrhonizers-label>`.