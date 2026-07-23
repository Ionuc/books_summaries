.. _java-development-concurrency-thread-pool-label:

Executors
=========

Executor
--------
- is a interface
- object of executor types execute submitted runnable tasks
- provides a way to decouple task submission from the mechanics of how task will be executed
- is alternative of creating and executing Thread objects
- can execute asynchronous tasks and tipically manage a pool of threads

ThreadPool
---------
- is a software design patter in multi-threading programming
- maintains multiple threads waiting for tasks to be allocated for concurrent execution by the supervision program
- threads are reused and not recreated always
- Task is passed to a ThreadPool Executor
- Task is a runnable
- Runnable interface does not give information regarding completion, what the result was, or if an exception was thrown
- Before, the completion was done by 
    - using join
    - save results into shared memory
    - catch / report any unchecked exceptions, or using uncaught exception handlers

ExecutorService interface
-------------------------
- is an interface extending Excutor
- adds new behavior to:
    - terminate all threads
    - not complete tasks
    - submit task what will return a Future object

- methods:
    - submit(Callable<>): Future<>
    - submit(Runnale): Future<>
    - invokeAll(Collection<Callable<>>): Future<>
        - submit all tasks from collection
        - return a List<Future<>> for each callable result
    - invokeAll(Collection<Callable<>>, long, TimeUnit): Future<>
        - submit all tasks with timeout
        - throw InterruptException in case timeout was reached

    .. code-block:: python
       :linenos:

        public class InvokeAllDemo {
            
            public static void main(String[] args) throws InterruptedException {
                ExecutorService es = Executors.newCachedThreadPool();
                List<Callable<String>> tasks = new ArrayList<>(Arrays.asList(
                            () -> "task #1",
                            () -> "task #2",
                            () -> "task #3"
                        ));
                
                List<Future<String>> futures = es.invokeAll(tasks);
                
                futures.stream().map(future -> {
                    try {
                        return future.get();
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                    return "";
                }).forEach(System.out::println);
                
            }
        }


    - invokeAny(Collection<Callable<>):
        - invokes any of callables from collections
        - returns the completion result of the first task


    - invokeAny(Collection<Callable<>, long, TimeUnit):
        - invokes any of callables from collections
        - returns the completion result of the first task
        - throw InterruptException in case timeout was reached


    .. code-block:: python
       :linenos:


        public class InvokeAnyDemo {

            public static void main(String[] args) throws InterruptedException, ExecutionException {
                ExecutorService es = Executors.newCachedThreadPool();
                List<Callable<String>> tasks = new ArrayList<>(Arrays.asList(
                            () -> {
                                TimeUnit.MILLISECONDS.sleep(500);
                                return "task #1";
                            },
                            () -> {
                                TimeUnit.MILLISECONDS.sleep(100);
                                return "task #2";
                            },
                            () -> {
                                TimeUnit.MILLISECONDS.sleep(300);
                                return "task #3";
                            }
                        ));
                
                String result = es.invokeAny(tasks);
                
                System.out.println(result);
            }
            
        }


    - shutdown:
        - executorService will need to shutdown in case the app is finished and to stop also the JVM
        - will make executor service to stop accepting new tasks
        - will shutdown after all tasks will be finished
        - RejectExecutionException is thrown when new tasks are added while shutting down
        - is recommended to use in combination with awaitTermination()  shutdownNow() to not wait for infinte time (deadlock) to shutdown

    - awaitTermination:
        - await a specific amount of time for the ExecutorService to shutdown
        - throw InterruptException in case ExecutorService did not shutdown in the amount of time
    - shutdownNow
        - does not wait for nothing and just attepmts to stop all executing tasks
        - returns the list fo tasks that did not finished in the timeout time

ScheduedExecutorService interface
---------------------------------
- interface extending ExecutorService
- methods:
    - schedule(Runnable):
        - run a tasks once after a specific delay
    - schedule(Callable):
        - run a tasks once after a specific delay
    - scheduleAtFixRate(Runnale, initialDelay, period, timeUnit)
        - run a task after a specific initial delay and then run it repeatedly with a certain period
        - the period is a time measured etween the starting times of the tasks
        - execution rate is fixed
    - scheduledWithFixedDelay(Runnable, initialDelay, delay, timeUnit)
        - run a task after a specific initial delay and then run it repeatedly with a certain period
        - delay is measured between the end of the previous task and start of the next task
        - execution rate may vary depending on the time it takes to run any given task

Executor Hierarchy
------------------
- Executor (interface):
    - execute(command:Runnable):void
- ExecutorService<T>
    - shutdown():void -> allows all task to finish
    - shutdownNow(): List<Runnable> -> also stops those that are in progress
    - isShutdown(): boolean -> return true when we call shutdown() method
    - isTerminated(): boolean -> will return true when all task are completed or canceled
    - awaitTermination(timeout: long, unit: TimeUnit): boolean
    - submit(task: Calleble<T>): Future<T>
    - submit(task: Runnable): Future<?> -> will return a result of null or completion
    - submit(task: Runnable, result: T): Future<T> -> return that result when the task is done as uses it as the Futures return type

		- ...
- AbstractExecutorService:
    - provides impl for submit, invokeAll, invokeAny
- ThreadPoolExecutor:
    - implements Executor's run method
    - remove(task:Runnable): boolean -> removes Runnable we are already submitted
    - purge():void -> will remove all cancelled tasks
    - provides a constructor where we can set the corePoolSize, workQueue, threadFactory, RejectExecutionHandler and so on
    - it's rejectExecution() method is called if due to the limits in the queue size we can't accept the task

RejectedExecutionHandler
------------------------
- CallerRunsPolicy
    - as long the Executor is not shut down, the thread that tried to submit it runs a task instead
    - could lead to starvation
- AbortPolicy ( default )
    - throws RejectedExecutionException
- DiscardPolicy
    - does nothing
    - the task will silently discarded
    - can be used in dealing with live video streams
- DiscardOldestPolicy
    -  will run the task instead of the oldest waiting taks, as long as the executor is not shut down
    - is useful when we are interested in later task results rather than earlier ones

Executors types
---------------
- fixed size
- single thread
- sheduled


Executors
---------
- is a helper methods which is a factory method for ExecutorService instances
- methods:
    - newFixedThreadPool(nrOfThreads)
        - returns a new ThreadPoolExecutor with:
            - corePoolSize = nrOfThreads
            - maximumPoolSize = nrOfThreads
            - keepAliveTime = 0 seconds
            - LinkedBlockingQueue
            - workQueue: SyncrhonousQueue
    - newFixedThreadPool(int, ThreadFactory)
        - returns a new ThreadPoolExecutor with the given nr of threads and ThreadFactory
    - newCachedThreadPool()
        - returns a new ThreadPoolExecutor with:
            - corePoolSize =0
            - maximumPoolSize = MAX_VALUE
            - keepAliveTime = 60 seconds
            - workQueue: SyncrhonousQueue
        - this implementation may grow without bounds to accomodate any number of submitted tasks
        - when threads are not needed anymore, they will be disposed of after 60 seconds of inactivity
        - is useful when you have a lot of short living tasks in application
        - in a synchronouse queue, each operation must wait for a corresponding remove operation
        - pairs of add & remove operation are synchornously
        - this means the queue never actually contains anything
        - disadvantages:
            - each new task will create a new thread which can lead to Memory error or thread starvation

    - newSingleThreadExecutor:
        - returns an ExecutorService that manages one thread
        - in case the thread is terminated with failure, a new Thread will be created
        - tasks are guaranteed to execute sequentially and no more than one task will be active at any given time

    - newScheduledThreadPool(int):
        - returns a ScheduledThreadPoolExecutor with nr of corePoolsize
    - newScheduledThreadPool(int, ThreadFactory):
        - returns a ScheduledThreadPoolExecutor with nr of corePoolsize and a ThreadFactory
    
    - newSingleThreadScheduledExecutor():
        - returns a ScheduledThreadPoolExecutor which will process only one thread at given time

    - newWorkStealingPool:
        - returns a ForkJoinPool instance

Executor Implementations
------------------------
- ThreadPoolExecutor:
    - extends AbstractExecutorService
    - fields:
        - corePoolSize
            - nr of threads to keep in the pool even if they are idle
        - maximumPoolSize
        - keepAliveTime:
            - the maximum time tat access idle threads will wait for new tasks before terminating
    - it takes an int as method argument representing the nr of threads to use
    - the nr of threads is a fix number
    - new Thread will be created in case other thread is failing and closed
    - in case nr of threads is lower than available core processors, then those processors won't be used by this executor
    - in case nr of threads is bigger than avialable core processors, then it will process in parrallel only the nr of processors available
    - ideal number of threads should be computed based on limits:
        - nr of available CPUs:
            - Runtime.getRuntime().availableProcessors() cna provide the nr of available cores, but wrong value in case application runs on containers
        - nr of connections to a DB (in case the thread is interracting with DB)
        - nr of request that other server can process (in case threads send request to other servers)
    - methods:
        - getPoolSize()
        - getQueue()
- ScheduledThreadPoolExecutor:
    - it extends ThreadPoolExecutor
    - it implements ScheduledExecutorService

ThreadFactory
-------------
- objects of this type are used to create new threads by giving implementation to the newThread(Runnable) method
- it is used to make ExecutorService to work with custom Thread implementation

    .. code-block:: python
       :linenos:


        class DefaultThreadFactory implements ThreadFactory {
            
            private AtomicInteger counter = new AtomicInteger();

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "Custom Thread Factory | Thread #" + counter.getAndIncrement());
            }
            
        }

Number of threads formula
--------------------------
- NumberOfThreads = NumberOfAvailableCores * TargetCPUUtilization* (1 + WaitTime/ServiceTime):
    - NumberOfAvailableCores:
        - nr of available cores
    - WaitTime:
        - is the time spent waiting for input and output bound tasks to complete
        - ex:
            - amount of time you are waiting for HTTP response from remove server
    - ServiceTime:
        - the time spent being busy for processing the task
    - TargetCPUUtilization:
        - in case there are more multiple ExecutorsServices configured
        - is a nr between [0,1]:
            - 0 => executorService won't use at all CPU
            - 1 => executorService will use 100% of CPU
- the value WaitTime/ServiceTime is also called Blocking coefficient
- example:
    - a computation intensive task has blocking coefficient close to zero:
        - this means, for computation intensive tasks, NumberOfThreads = NumberOfAvailableCores
    - send request and wait response for data:
        - program reads batch of data and process it
        - process of reading atch of request takes 200 millis
        - time of processing this data = 20
        - NumberOfThreads = NumberOfAvailableCores(1 + 200/20) = NumberOfAvailableCores * 11
        - it sounds a big amount of threads, but these threads are waiting 200 millis just to receive new batches, in which time they do nothing, just wait


Safely close excutors
---------------------
    - first call shutdown()
    - await a timeout for termination
    - call shutdownNow in the finally block


    .. code-block:: python
       :linenos:


        public class ExecutorServiceInterruptionDemo {
            
            public static void main(String[] args) throws InterruptedException {
                ExecutorService es = Executors.newCachedThreadPool();
                IntStream.range(0, 10).forEach((i) -> {
                    es.submit(() -> {
                        try {
                            TimeUnit.SECONDS.sleep(i);
                            System.out.println("Task #" + i + " is completed");
                        } catch (InterruptedException e) {
                            System.out.println("Task #" + i + " is interrupted");
                        }
                    });
                });
                
                System.out.println("Shutting down");
                es.shutdown();
                
                // Uncomment line below to see RejectedExecutionException
                // es.submit(() -> System.out.println("new task will throw exception after shutdown"));
                
                try {
                    es.awaitTermination(2, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    es.shutdownNow();
                }
                
                
            }

        }

Java 19 improvements
--------------------
- all ExecutionService implementation are now AutoClosable
    - they can be created inside try-with-resources block
    - the close methods is automatically closed, which will shutdown the executor and awaits task completion
    - this feature is used more with Virtual threads which are lighter to create

    .. code-block:: python
       :linenos:


        try (ExecutorService executor = Executors.newCachedThreadPool()) {
            // Submit a simple task
            Future<String> result = executor.submit(() -> {
                System.out.println("Hello from a thread in ExecutorService!");
                return "Task completed";
            });

            // Wait and print result
            System.out.println("Result: " + result.get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


Java 21 improvements
--------------------
- new ExecutorService implementation of virtual threads where introduced
    - Executors.newVirtualThreadPerTaskExecutor()
        - returns an instance of ExecutionService which will process Virtual threads


    .. code-block:: python
       :linenos:


        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            // Submit a simple task
            Future<String> result = executor.submit(() -> {
                System.out.println("Hello from a virtual thread in ExecutorService!");
                return "Task completed";
            });

            // Wait and print result
            System.out.println("Result: " + result.get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

:ref:`Go Back <java-development-concurrency-label>`.